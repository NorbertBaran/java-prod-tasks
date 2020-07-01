package uj.jwzp.vet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uj.jwzp.vet.model.visit.Visit;
import uj.jwzp.vet.model.visit.VisitResult;
import uj.jwzp.vet.service.VisitService;

import java.util.List;

@RestController
public class VisitController {

    VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService){
        this.visitService =visitService;
    }

    @GetMapping("visits/get/all")
    public List<Visit> getVisit(){
        return visitService.getVisits();
    }

    @PostMapping("visits/add/visit")
    public ResponseEntity<String> addVisit(@RequestBody Visit visit){
        ResponseEntity<String> responseEntity=visitService.addVisit(visit);
        System.out.println(responseEntity);
        return responseEntity;
    }

    @PostMapping("visits/set/result")
    public void setResult(@RequestParam Long visitId, @RequestBody VisitResult visitResult){
        visitService.setResult(visitId, visitResult);
    }

    @GetMapping("visits/get/animal")
    public List<Visit> getAnimalVisits(@RequestParam Long animalId){
        return visitService.getAnimalVisits(animalId);
    }

    @GetMapping("visits/get/client")
    public List<Visit> getClientVisits(@RequestParam Long clientId){
        return visitService.getClientVisits(clientId);
    }

    @GetMapping("visits/get/today")
    public List<Visit> getTodayVisits(){
        return visitService.getTodayVisits();
    }
}
