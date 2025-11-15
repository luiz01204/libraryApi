package br.dev.luizmachado.LibraryApi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_usuário")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "login",nullable = false, unique = true,length = 30)
    private String login;

    @Column(name = "senha",nullable = false,length = 300)
    private String password;

    @Column(name = "regra_acesso")
    @Enumerated(EnumType.STRING)
    private Role role;
}
