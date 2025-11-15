package br.dev.luizmachado.LibraryApi.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record AuthorRequestDto(
        UUID id,
        @NotBlank(message = "Campo name não pode ser branco!")
        @Size(min = 2, max = 100, message = "Campo name não pode ter menos que 2 caracteres e no máximo 100 caracteres.")
        String name,

        @NotBlank(message = "Campo nationality não pode ser branco!")
        @Size(min = 2, max = 100, message = "Campo name não pode ter menos que 2 caracteres e no máximo 100 caracteres.")
        String nationality
) {}
