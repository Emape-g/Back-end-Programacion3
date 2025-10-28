package com.example.foodstore.exception;

public class UsuarioExistenteException extends RuntimeException{
    public UsuarioExistenteException(String message){
        super(message);
    }
}
