package uj.jwzp.vet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uj.jwzp.vet.model.Animal;
import uj.jwzp.vet.model.Client;
import uj.jwzp.vet.service.AnimalService;
import uj.jwzp.vet.service.ClientService;

import java.util.List;

@RestController
public class AnimalController {

    AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService){
        this.animalService=animalService;
    }

    @GetMapping("/animals/get/all")
    public List<Animal> getAnimals(){
        return animalService.getClients();
    }

    @PostMapping("animals/add/animal")
    public void addAnimal(@RequestBody Animal animal){
        animalService.addClient(animal);
    }

    @PostMapping("animals/set/owner")
    public void setOwner(@RequestParam Long clientId, @RequestParam Long animalId){
        animalService.setOwner(clientId, animalId);
    }

    @PostMapping("animals/delete/owner")
    public void deleteOwner(@RequestParam Long animalId){
        animalService.deleteOwner(animalId);
    }

}
