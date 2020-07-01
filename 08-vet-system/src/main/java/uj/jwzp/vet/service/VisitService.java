package uj.jwzp.vet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uj.jwzp.vet.model.visit.Visit;
import uj.jwzp.vet.model.visit.VisitResult;
import uj.jwzp.vet.repository.VisitRepo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class VisitService {

    VisitRepo visitRepo;

    @Autowired
    public VisitService(VisitRepo visitRepo){
        this.visitRepo =visitRepo;
    }

    public List<Visit> getVisits(){
        return visitRepo.findAll();
    }

    public ResponseEntity<String> addVisit(Visit visit) {

        LocalDate today=LocalDate.now();

        //LocalTime visitTimeEnd=LocalTime.of(visit.getFinish().getHour(), visit.getFinish().getMinute());

        if(!visit.getDate().isAfter(today))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Before tomorrow");
        if(visit.getBegin().isBefore(LocalTime.of(8, 0)) || visit.getFinish().isAfter(LocalTime.of(20, 0)))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Closed vet");
        if(visitRepo.findCollision(visit.getDate(), visit.getBegin(), visit.getFinish()).size()>0)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Collided visit");
        visitRepo.save(visit);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public void setResult(Long visitId, VisitResult visitResult) {
        Visit visit=visitRepo.findById(visitId).orElse(null);
        if(visit!=null){
            visit.setDescription(visitResult.getDescription());
            visit.setStatus(visitResult.getStatus());
            visitRepo.save(visit);
        }
    }

    public List<Visit> getAnimalVisits(Long animalId) {
        return visitRepo.findAllByPatient_Id(animalId);
    }

    public List<Visit> getClientVisits(Long clientId) {
        return visitRepo.findAllByPatient_Owner_Id(clientId);
    }

    public List<Visit> getTodayVisits() {
        return visitRepo.findAllByDate(LocalDate.now());
    }
}
