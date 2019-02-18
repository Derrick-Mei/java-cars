package com.dkm.javacars;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
// makes object ready for JPA storage
@Entity
public class AdCar
{
    private @Id @GeneratedValue Long id;
    private int year;
    private String brand;
    private String model;

    // default constructor for JPA
    public AdCar()
    {
    }

    public AdCar(int year, String brand, String model)
    {
        this.year = year;
        this.brand = brand;
        this.model = model;
    }
}
