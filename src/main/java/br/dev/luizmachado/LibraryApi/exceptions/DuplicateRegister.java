package br.dev.luizmachado.LibraryApi.exceptions;

public class DuplicateRegister extends RuntimeException {
    public DuplicateRegister(String message) {
        super(message);
    }
}
