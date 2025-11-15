package br.dev.luizmachado.LibraryApi.mappers;

import br.dev.luizmachado.LibraryApi.dto.AuthorRequestDto;
import br.dev.luizmachado.LibraryApi.dto.AuthorResponseDto;
import br.dev.luizmachado.LibraryApi.models.AuthorModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "books", ignore = true)
    AuthorModel toModel(AuthorRequestDto dto);

    AuthorResponseDto toDto(AuthorModel model);
}

