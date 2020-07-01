package uj.jwzp.vet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uj.jwzp.vet.model.Animal;
import uj.jwzp.vet.model.Client;

import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
}
