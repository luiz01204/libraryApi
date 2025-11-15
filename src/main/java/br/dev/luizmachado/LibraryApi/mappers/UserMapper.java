package br.dev.luizmachado.LibraryApi.mappers;

import br.dev.luizmachado.LibraryApi.dto.UserRequestDto;
import br.dev.luizmachado.LibraryApi.models.UserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserModel toModel(UserRequestDto dto);
}
