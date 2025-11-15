package br.dev.luizmachado.LibraryApi.controllers;

import br.dev.luizmachado.LibraryApi.dto.BookRequestDto;
import br.dev.luizmachado.LibraryApi.dto.BookResponseDto;
import br.dev.luizmachado.LibraryApi.dto.PageResponse;
import br.dev.luizmachado.LibraryApi.mappers.BookMapper;
import br.dev.luizmachado.LibraryApi.models.BookModel;
import br.dev.luizmachado.LibraryApi.models.Gender;
import br.dev.luizmachado.LibraryApi.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;
    private final BookMapper mapper;

    @PostMapping
    public ResponseEntity<BookResponseDto> create(@Valid @RequestBody BookRequestDto dto) {
        BookModel book = mapper.toModel(dto);
        BookModel bookSaved = service.create(book);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(book.getId())
                .toUri();
        return ResponseEntity.created(location).body(mapper.toDto(bookSaved));
    }

    @GetMapping("/all")
    public ResponseEntity<PageResponse<BookResponseDto>> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<BookResponseDto> pageResult = service.getAll(page,size).map(mapper::toDto);
        PageResponse<BookResponseDto> response = new PageResponse<>(
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
    public ResponseEntity<BookResponseDto> getById(@PathVariable("id") UUID id) {
        BookModel book = service.getById(id);
        return ResponseEntity.ok(mapper.toDto(book));
    }

    @GetMapping
    public ResponseEntity<PageResponse<BookResponseDto>> search(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "gender", required = false) Gender gender,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Page<BookModel> books = service.searchBooks(title, gender,page,size);
        Page<BookResponseDto> pageResult = books.map(mapper::toDto);
        PageResponse<BookResponseDto> response = new PageResponse<>(
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
    public ResponseEntity<BookResponseDto> update(
            @Valid
            @PathVariable("id") UUID id,
            @RequestBody BookRequestDto dto
    ) {

        BookModel book = mapper.toModel(dto);
        BookModel bookUpdated = service.update(id, book);
        if (bookUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.toDto(bookUpdated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") UUID id) {
        BookModel book = service.getById(id);
        service.deleteBYId(id);
        return ResponseEntity.noContent().build();
    }
}
