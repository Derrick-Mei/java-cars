package com.dkm.javacars;

class AeCarNotFoundByIdException extends RuntimeException
{
    public AeCarNotFoundByIdException(Long id)
    {
        super("NOT FOUND: Car with Id: " + id);
    }
}
