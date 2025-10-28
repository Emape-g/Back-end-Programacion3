package com.example.foodstore.exception;


public class UsuarioNoEncontradoException extends RuntimeException{
    public UsuarioNoEncontradoException(String message){
        super(message);
    }
}
