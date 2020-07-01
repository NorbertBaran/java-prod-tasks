package uj.jwzp.vet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uj.jwzp.vet.model.Animal;
import uj.jwzp.vet.model.Client;
import uj.jwzp.vet.repository.AnimalRepo;
import uj.jwzp.vet.repository.ClientRepo;

import java.util.List;

@Service
public class AnimalService {

    AnimalRepo animalRepo;
    ClientRepo clientRepo;

    @Autowired
    public AnimalService(AnimalRepo animalRepo, ClientRepo clientRepo){
        this.animalRepo=animalRepo;
        this.clientRepo=clientRepo;
    }

    public List<Animal> getClients(){
        return animalRepo.findAll();
    }

    public void addClient(Animal client) {
        animalRepo.save(client);
    }

    public void setOwner(Long clientId, Long animalId) {
        Animal animal=animalRepo.findById(animalId).orElse(null);
        Client client=clientRepo.findById(clientId).orElse(null);
        animal.setOwner(client);
        animalRepo.save(animal);
    }

    public void deleteOwner(Long animalId) {
        Animal animal=animalRepo.findById(animalId).orElse(null);
        animal.setOwner(null);
        animalRepo.save(animal);
    }
}
