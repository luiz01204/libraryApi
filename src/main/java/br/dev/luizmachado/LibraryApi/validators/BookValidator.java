package br.dev.luizmachado.LibraryApi.validators;

import br.dev.luizmachado.LibraryApi.exceptions.DuplicateRegister;
import br.dev.luizmachado.LibraryApi.models.BookModel;
import br.dev.luizmachado.LibraryApi.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookValidator {

    private final BookRepository repository;

    public void validateDuplicate(BookModel model){
        repository.findFirstByTitleAndGender(model.getTitle(),model.getGender())
                .ifPresent(existing -> {
                    throw new DuplicateRegister("Livro duplicado!");
                });
    }
}
