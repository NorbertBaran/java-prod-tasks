package uj.jwzp.vet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uj.jwzp.vet.model.Animal;
import uj.jwzp.vet.model.Client;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {
}
