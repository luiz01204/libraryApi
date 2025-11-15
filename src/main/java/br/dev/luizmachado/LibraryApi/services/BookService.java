package br.dev.luizmachado.LibraryApi.services;

import br.dev.luizmachado.LibraryApi.exceptions.AuthorNotFound;
import br.dev.luizmachado.LibraryApi.exceptions.BookNotFound;
import br.dev.luizmachado.LibraryApi.models.AuthorModel;
import br.dev.luizmachado.LibraryApi.models.BookModel;
import br.dev.luizmachado.LibraryApi.models.Gender;
import br.dev.luizmachado.LibraryApi.models.UserModel;
import br.dev.luizmachado.LibraryApi.repositories.AuthorRepository;
import br.dev.luizmachado.LibraryApi.repositories.BookRepository;
import br.dev.luizmachado.LibraryApi.security.SecurityService;
import br.dev.luizmachado.LibraryApi.validators.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final BookValidator validator;
    private final SecurityService security;

    public BookModel create(BookModel model){
        AuthorModel authorModel = authorRepository.findById(model.getAuthor().getId())
               .orElseThrow(AuthorNotFound::new);
       model.setAuthor(authorModel);
       validator.validateDuplicate(model);
       UserModel user = security.getUserLogger();
       model.setUserId(user.getId());
       return repository.save(model);
    }

    public BookModel getById(UUID id){
        return repository.findById(id).orElseThrow(BookNotFound::new);
    }

    public Page<BookModel> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return repository.findAll(pageable);
    }

    public void deleteBYId(UUID id){
        repository.findById(id).orElseThrow(BookNotFound::new);
        repository.deleteById(id);
    }

    public BookModel update(UUID id, BookModel model){
        BookModel book = repository.findById(id).orElseThrow(BookNotFound::new);
        book.setTitle(model.getTitle());
        book.setPublicationDate(model.getPublicationDate());
        book.setPrice(model.getPrice());
        book.setGender(model.getGender());
        validator.validateDuplicate(model);
        return repository.save(book);
    }

    public Page<BookModel> searchBooks(String title, Gender gender, int page, int size){
        BookModel book = new BookModel();
        book.setTitle(title);
        book.setGender(gender);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<BookModel> bookModelExample = Example.of(book,matcher);
        Pageable pageable = PageRequest.of(page,size);
        return repository.findAll(bookModelExample,pageable);
    }

}
