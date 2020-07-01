package uj.jwzp.vet.model.visit;

import uj.jwzp.vet.model.Animal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne
    Animal patient;
    LocalDate date;
    LocalTime begin;
    //@Enumerated(EnumType.STRING)
    int duration;
    //@JsonIgnore
    //LocalTime end;
    LocalTime finish;
    String description;
    @Enumerated(EnumType.STRING)
    VisitStatus status=VisitStatus.APPOINTMENT;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Animal getPatient() {
        return patient;
    }

    public void setPatient(Animal patient) {
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getBegin() {
        return begin;
    }

    public void setBegin(LocalTime begin) {
        this.begin = begin;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(VisitDuration duration) {
        this.duration = duration.getValue();
        //this.end = begin.plusMinutes(this.duration);
        this.finish = begin.plusMinutes(this.duration);
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getFinish(){
        //return end;
        return finish;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VisitStatus getStatus() {
        return status;
    }

    public void setStatus(VisitStatus status) {
        this.status = status;
    }
}
