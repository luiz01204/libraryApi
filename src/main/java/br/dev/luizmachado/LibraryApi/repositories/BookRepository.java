package br.dev.luizmachado.LibraryApi.repositories;

import br.dev.luizmachado.LibraryApi.models.BookModel;
import br.dev.luizmachado.LibraryApi.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<BookModel,UUID> {

    boolean existsByAuthor_Id(UUID id);
    Optional<BookModel> findFirstByTitleAndGender(String title, Gender gender);
}
