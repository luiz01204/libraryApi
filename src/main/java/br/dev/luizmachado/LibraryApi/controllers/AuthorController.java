package br.dev.luizmachado.LibraryApi.controllers;

import br.dev.luizmachado.LibraryApi.dto.AuthorRequestDto;
import br.dev.luizmachado.LibraryApi.dto.AuthorResponseDto;
import br.dev.luizmachado.LibraryApi.dto.PageResponse;
import br.dev.luizmachado.LibraryApi.mappers.AuthorMapper;
import br.dev.luizmachado.LibraryApi.models.AuthorModel;
import br.dev.luizmachado.LibraryApi.services.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService service;
    private final AuthorMapper mapper;

    @PostMapping
    public ResponseEntity<AuthorResponseDto> save(@Valid @RequestBody AuthorRequestDto authorRequestDto) {
        AuthorModel author = mapper.toModel(authorRequestDto);
        AuthorModel authorSaved = service.create(author);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(author.getId())
                .toUri();
        return ResponseEntity.created(location).body(mapper.toDto(authorSaved));
    }

    @GetMapping("/all")
    public ResponseEntity<PageResponse<AuthorResponseDto>> getAll(
            @RequestParam(value = "page", defaultValue = "0")  int page,
            @RequestParam(value = "size", defaultValue = "10") int size
            ) {
        Page<AuthorResponseDto> pageResult = service.getAll(page,size).map(mapper::toDto);
        PageResponse<AuthorResponseDto> response = new PageResponse<>(
                pageResult.getContent(),
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages(),
                pageResult.isFirst(),
                pageResult.isLast()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> getById(@PathVariable("id") UUID id) {
        AuthorModel author = service.getById(id);
        return ResponseEntity.ok(mapper.toDto(author));
    }

    @GetMapping()
    public ResponseEntity<PageResponse<AuthorResponseDto>> searchAuthor(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "nationality", required = false) String nationality,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<AuthorModel> authors = service.searchAuthor(name, nationality,page,size);
        Page<AuthorResponseDto> pageResult = authors.map(mapper::toDto);
        PageResponse<AuthorResponseDto> response = new PageResponse<>(
                pageResult.getContent(),
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages(),
                pageResult.isFirst(),
                pageResult.isLast()
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> update(
            @Valid
            @PathVariable("id") UUID id,
            @RequestBody AuthorRequestDto dto
    ) {
        AuthorModel model = mapper.toModel(dto);
        AuthorModel author = service.update(id, model);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.toDto(author));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();

    }
}
