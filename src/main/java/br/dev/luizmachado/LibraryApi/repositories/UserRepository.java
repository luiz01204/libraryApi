package br.dev.luizmachado.LibraryApi.repositories;

import br.dev.luizmachado.LibraryApi.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    Optional<UserModel> findByLogin(String login);
}
