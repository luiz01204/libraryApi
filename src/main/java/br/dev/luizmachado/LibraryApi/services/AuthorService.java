package br.dev.luizmachado.LibraryApi.services;

import br.dev.luizmachado.LibraryApi.exceptions.AuthorNotFound;
import br.dev.luizmachado.LibraryApi.models.AuthorModel;
import br.dev.luizmachado.LibraryApi.models.UserModel;
import br.dev.luizmachado.LibraryApi.repositories.AuthorRepository;
import br.dev.luizmachado.LibraryApi.security.SecurityService;
import br.dev.luizmachado.LibraryApi.validators.AuthorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;
    private final AuthorValidator validator;
    private final SecurityService security;

    public AuthorModel create(AuthorModel model){
        validator.validateDuplicity(model);
        UserModel user = security.getUserLogger();
        model.setUserId(user.getId());
        repository.save(model);
        return model;
    }

    public AuthorModel getById(UUID id){
        return repository.findById(id).orElseThrow(AuthorNotFound::new);
    }

    public Page<AuthorModel> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return repository.findAll(pageable);
    }

    public void deleteById(UUID id){
        repository.findById(id).orElseThrow(AuthorNotFound::new);
        validator.validateDeletion(id);
        repository.deleteById(id);
    }

    public AuthorModel update(UUID id, AuthorModel model){
        AuthorModel author = repository.findById(id).orElseThrow(AuthorNotFound::new);
        author.setName(model.getName());
        author.setNationality(model.getNationality());
        validator.validateDuplicity(author);
        return repository.save(author);
    }

    public Page<AuthorModel> searchAuthor(String name, String nationality, int page, int size){
        AuthorModel author = new AuthorModel();
        author.setName(name);
        author.setNationality(nationality);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<AuthorModel> authorExample = Example.of(author, matcher);
        Pageable pageable = PageRequest.of(page,size);
        return repository.findAll(authorExample,pageable);
    }
}
