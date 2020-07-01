package uj.jwzp.vet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uj.jwzp.vet.model.Animal;
import uj.jwzp.vet.model.Client;
import uj.jwzp.vet.repository.AnimalRepo;
import uj.jwzp.vet.repository.ClientRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLOutput;
import java.util.List;

@Service
public class ClientService {

    ClientRepo clientRepo;
    AnimalRepo animalRepo;

    @Autowired
    public ClientService(ClientRepo clientRepo, AnimalRepo animalRepo){
        this.clientRepo=clientRepo;
        this.animalRepo=animalRepo;
    }

    public List<Client> getClients(){
        return clientRepo.findAll();
    }

    public void addClient(Client client) {
        clientRepo.save(client);
    }
}
