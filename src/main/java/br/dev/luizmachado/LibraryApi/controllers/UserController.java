package br.dev.luizmachado.LibraryApi.controllers;

import br.dev.luizmachado.LibraryApi.dto.UserRequestDto;
import br.dev.luizmachado.LibraryApi.mappers.UserMapper;
import br.dev.luizmachado.LibraryApi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserRequestDto dto){
        service.create(mapper.toModel(dto));
    }
}
