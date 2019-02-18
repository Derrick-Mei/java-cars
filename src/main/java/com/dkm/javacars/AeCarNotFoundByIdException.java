package com.dkm.javacars;

public class AeCarNotFoundByIdException extends RuntimeException
{
    public AeCarNotFoundByIdException(Long id)
    {
        super("NOT FOUND: Car with Id: " + id);
    }
}
