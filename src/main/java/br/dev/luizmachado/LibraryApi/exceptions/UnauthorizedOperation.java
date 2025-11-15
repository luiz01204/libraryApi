package br.dev.luizmachado.LibraryApi.exceptions;

public class UnauthorizedOperation extends RuntimeException {
    public UnauthorizedOperation(String message) {
        super(message);
    }
}
