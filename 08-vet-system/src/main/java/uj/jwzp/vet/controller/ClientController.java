package uj.jwzp.vet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uj.jwzp.vet.model.Animal;
import uj.jwzp.vet.model.Client;
import uj.jwzp.vet.service.ClientService;

import java.util.List;

@RestController
public class ClientController {

    ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService=clientService;
    }

    @GetMapping("clients/get/all")
    public List<Client> getClients(){
        return clientService.getClients();
    }

    @PostMapping("clients/add/client")
    public void addClient(@RequestBody Client client){
        clientService.addClient(client);
    }

}
