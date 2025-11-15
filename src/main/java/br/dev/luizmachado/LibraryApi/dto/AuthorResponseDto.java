package br.dev.luizmachado.LibraryApi.dto;

import java.util.UUID;

public record AuthorResponseDto(
        UUID id,
        String name,
        String nationality
) {}
