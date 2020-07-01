package uj.jwzp.vet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uj.jwzp.vet.model.Animal;
import uj.jwzp.vet.model.visit.Visit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface VisitRepo extends JpaRepository<Visit, Long> {
    @Query("select v from Visit v where v.date=?1 and v.begin < ?3 and v.finish> ?2")
    public List<Visit> findCollision(LocalDate date, LocalTime begin, LocalTime end);

    public List<Visit> findAllByPatient_Id(Long id);

    public List<Visit> findAllByPatient_Owner_Id(Long id);

    public List<Visit> findAllByDate(LocalDate date);
}
