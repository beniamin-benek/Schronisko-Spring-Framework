package com.ben.schronisko2.controller;

import com.ben.schronisko2.model.Animal;
import com.ben.schronisko2.model.Category;
import com.ben.schronisko2.repository.AnimalRepository;
import com.ben.schronisko2.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AppController {

    private AnimalRepository animalRepository;
    private CategoryRepository categoryRepository;

    public AppController(AnimalRepository animalRepository, CategoryRepository categoryRepository) {
        this.animalRepository = animalRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public String showHomepage(Model model) {
        List<Animal> animals = animalRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("animals", animals);
        model.addAttribute("categories", categories);
        return "home";
    }

    @GetMapping("/details")
    public String showDetails(Model model, @RequestParam int id) {
        Animal animal = animalRepository.findByID(id);
        model.addAttribute("animal", animal);
        if (animalRepository.checkIfExists(id))
            return "details";
        else
            return "redirect:/";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("newAnimal", new Animal());
        return "add";
    }

    @PostMapping("/post")
    public String addAnimal(Animal newAnimal) {
        animalRepository.setIdValue(newAnimal);
        System.out.println(newAnimal.getId());
        animalRepository.add(newAnimal);
        return "redirect:/details?id=" + newAnimal.getId();
    }

    @GetMapping("/edit")
    public String showEditionForm(Model model, @RequestParam int id) {
        if (animalRepository.checkIfExists(id)) {
            List<Category> categories = categoryRepository.findAll();
            Animal editedAnimal = animalRepository.findByID(id);
            System.out.println(editedAnimal.toString());
            System.out.println(animalRepository.findPositionOnList(editedAnimal) + " | " +editedAnimal.getId() + ", " + editedAnimal.getName());
            model.addAttribute("categories", categories);
            model.addAttribute("editedAnimal", editedAnimal);
            return "edit";
        }
        else return "redirect:/";
    }

    @PostMapping("/post-edited")
    public String editAnimal(Animal editedAnimal) {
        animalRepository.replace(editedAnimal);
        animalRepository.showAnimalList(); //do testów
        return "redirect:/details?id=" + editedAnimal.getId();
    }

    @GetMapping("/category")
    public String showAnimalsFromCategory(Model model, @RequestParam String name) {
        List<Animal> animalsFromCategory = animalRepository.findAnimalsFromCategory(new Category(name));
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("category", name);
        model.addAttribute("animals", animalsFromCategory);
        return "animals-from-category";
    }
}
