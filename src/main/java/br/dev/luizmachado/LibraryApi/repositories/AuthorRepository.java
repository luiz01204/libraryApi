package br.dev.luizmachado.LibraryApi.repositories;

import br.dev.luizmachado.LibraryApi.models.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<AuthorModel,UUID> {

    List<AuthorModel> findByName(String name);
    List<AuthorModel> findByNationality(String nationality);
    List<AuthorModel> findByNameAndNationality(String name, String nationality);
    Optional<AuthorModel> findFirstByNameAndNationality(String name, String nationality);
}
