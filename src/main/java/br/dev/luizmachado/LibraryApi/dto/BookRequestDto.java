package br.dev.luizmachado.LibraryApi.dto;

import br.dev.luizmachado.LibraryApi.models.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.UUID;

public record BookRequestDto(
        @NotBlank(message = "Campo não pode está em branco!")
        String title,
        @NotNull(message = "Campo não pode está em branco!")
        @Past(message = "Não pode datas futuras!")
        LocalDate publicationDate,
        String price,
        Gender gender,
        @NotNull(message = "Campo não pode está em branco!")
        UUID authorId
) {}
