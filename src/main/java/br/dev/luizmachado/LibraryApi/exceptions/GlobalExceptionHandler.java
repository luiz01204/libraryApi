package br.dev.luizmachado.LibraryApi.exceptions;

import br.dev.luizmachado.LibraryApi.dto.FieldErroDto;
import br.dev.luizmachado.LibraryApi.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<FieldErroDto> errorsDto = fieldErrors.stream()
                .map(fe -> new FieldErroDto(
                        fe.getField(), fe.getDefaultMessage()
                )).toList();
        return new ErrorResponseDto(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação!",
                errorsDto);
    }

    @ExceptionHandler(DuplicateRegister.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDto handlerDuplicateRegisterError(DuplicateRegister e){
        return ErrorResponseDto.errorConflict(e.getMessage());
    }

    @ExceptionHandler(AuthorNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handlerAuthorNoFoundError(AuthorNotFound e){
        return ErrorResponseDto.errorNotFound("Autor não encontrado!");
    }

    @ExceptionHandler(BookNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handlerBookNoFoundError(BookNotFound e){
        return ErrorResponseDto.errorNotFound("Livro não encontrado!");
    }

    @ExceptionHandler(UnauthorizedOperation.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponseDto handlerUnauthorizedError(UnauthorizedOperation e){
        return ErrorResponseDto.unauthorizedOperation(e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ErrorResponseDto handlerAccessDenied(AccessDeniedException e){
        return new ErrorResponseDto(
                HttpStatus.FORBIDDEN.value(),
                "Usuário não autorizado!!",
                List.of()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handlerInternalServerErrorException(Exception e){
        return new ErrorResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro interno!",
                List.of()
        );
    }
}
