package br.dev.luizmachado.LibraryApi.mappers;

import br.dev.luizmachado.LibraryApi.dto.BookRequestDto;
import br.dev.luizmachado.LibraryApi.dto.BookResponseDto;
import br.dev.luizmachado.LibraryApi.models.BookModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "author.id", source = "authorId")
    BookModel toModel(BookRequestDto dto);

    BookResponseDto toDto(BookModel model);

}
