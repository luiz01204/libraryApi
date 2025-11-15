package br.dev.luizmachado.LibraryApi.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorResponseDto(
        int status,
        String message,
        List<FieldErroDto> errors
) {

    public static ErrorResponseDto errorNotFound(String message){
        return new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), message,List.of());
    }

    public static ErrorResponseDto errorConflict(String message){
        return new ErrorResponseDto(HttpStatus.CONFLICT.value(),message,List.of());
    }

    public static ErrorResponseDto unauthorizedOperation(String message){
        return new ErrorResponseDto(HttpStatus.UNAUTHORIZED.value(),message,List.of());
    }
}
