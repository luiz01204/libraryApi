package br.dev.luizmachado.LibraryApi.dto;

import br.dev.luizmachado.LibraryApi.models.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BookResponseDto(
        UUID id,
        String title,
        LocalDate publicationDate,
        BigDecimal price,
        Gender gender,
        AuthorResponseDto author
) {}
