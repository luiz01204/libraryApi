package br.dev.luizmachado.LibraryApi.validators;

import br.dev.luizmachado.LibraryApi.exceptions.DuplicateRegister;
import br.dev.luizmachado.LibraryApi.exceptions.UnauthorizedOperation;
import br.dev.luizmachado.LibraryApi.models.AuthorModel;
import br.dev.luizmachado.LibraryApi.repositories.AuthorRepository;
import br.dev.luizmachado.LibraryApi.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthorValidator {

    private final AuthorRepository repository;
    private final BookRepository bookRepository;

    public void validateDuplicity(AuthorModel author){
        repository.findFirstByNameAndNationality(author.getName(),author.getNationality())
                .ifPresent(existing -> {
                    throw new DuplicateRegister("Autor duplicado!");
                });
    }

    public void validateDeletion(UUID id){
        if (bookRepository.existsByAuthor_Id(id)){
            throw new UnauthorizedOperation("Autor possui livros cadastrados!");
        }
    }
}
