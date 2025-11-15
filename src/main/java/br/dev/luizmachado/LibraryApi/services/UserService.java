package br.dev.luizmachado.LibraryApi.services;

import br.dev.luizmachado.LibraryApi.models.UserModel;
import br.dev.luizmachado.LibraryApi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public void create(UserModel userModel){
        userModel.setPassword(encoder.encode(userModel.getPassword()));
        repository.save(userModel);
    }

    public UserModel getByLogin(String login){
        return repository.findByLogin(login).orElseThrow();
    }
}
