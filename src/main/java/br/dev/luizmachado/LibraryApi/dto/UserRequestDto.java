package br.dev.luizmachado.LibraryApi.dto;

import br.dev.luizmachado.LibraryApi.models.Role;

public record UserRequestDto(
        String login,
        String password,
        Role role
) {}
