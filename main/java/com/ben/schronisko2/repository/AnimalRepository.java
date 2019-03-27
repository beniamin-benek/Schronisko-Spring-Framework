package com.ben.schronisko2.repository;

import com.ben.schronisko2.model.Animal;
import com.ben.schronisko2.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class AnimalRepository {

    private List<Animal> animals = new ArrayList<>();

    public AnimalRepository() {
        animals.add(0,new Animal(0,"Azor", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut luctus at dolor vel posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut luctus at dolor vel posuere.", "Azor. Integer magna nisl, mollis non pellentesque sit amet, elementum nec est. Sed et enim ac ante accumsan convallis aliquam nec urna. Nam pharetra cursus orci sed hendrerit. In vitae fermentum nisl. Quisque vel orci faucibus, ultricies sapien at, placerat metus. Nunc et ex finibus, fermentum quam nec, sollicitudin arcu. Nullam id lectus est. Nam sed leo ac nunc dignissim convallis.","http://images2.imagebam.com/f8/94/13/3da93a1174129814.jpg",new Category("Pies")));
        animals.add(1,new Animal(1,"Rudy", "Suspendisse quis tempor dui. Aliquam erat volutpat.", "Rudy. Integer magna nisl, mollis non pellentesque sit amet, elementum nec est. Sed et enim ac ante accumsan convallis aliquam nec urna. Nam pharetra cursus orci sed hendrerit. In vitae fermentum nisl. Quisque vel orci faucibus, ultricies sapien at, placerat metus. Nunc et ex finibus, fermentum quam nec, sollicitudin arcu. Nullam id lectus est. Nam sed leo ac nunc dignissim convallis.","http://thumbs2.imagebam.com/82/ab/6b/fd048d1174129824.jpg",new Category("Kot")));
    }

    public List<Animal> findAll() {
        return animals;
    }

    public void add(Animal animal) {
        animals.add(animal);
    }

    public void replace(Animal animal) {
        animals.set(animal.getId(), animal);
    }

    public void setIdValue(Animal animal) {
        animal.setId(animals.size());
    }

    public void showAnimalList(){ //do testów
        for (int i = 0; i < animals.size(); i++) {
            System.out.println("Pozycja: " + i + " | Index: " + animals.get(i).getId() + ", " + animals.get(i).getName() + " | " + animals.get(i));
            System.out.println("---------------------------");
        }
    }

    public List<Animal> findAnimalsFromCategory(Category category) {
        List<Animal> animalsFromCategory = new LinkedList<>();
        for (Animal animal : animals) {
            if (animal.getCategory().getName().equals(category.getName()))
                animalsFromCategory.add(animal);
        }
        return animalsFromCategory;
    }

    public int findPositionOnList(Animal animal) { //do testów
        for (int i = 0; i < animals.size(); i++) {
            if(animals.get(i).getId() == animal.getId())
                return i;
        }
        return -1;
    }

    public boolean checkIfExists(int id) {
        for (Animal animal : animals) {
            if (animal.getId() == id)
                return true;
        }
        return false;
    }

    public Animal findByID(int id) {
        for (Animal animal : animals) {
            if (animal.getId() == id)
                return animal;
        }
        return null;
    }

}