package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("CAR")
@Entity
public class Car extends Vehicle{
    @JsonProperty("spareTire")
    private boolean spareTire;
    @JsonProperty("convertible")
    private boolean convertible;

    public Car(int maxSpeed, Brand brand, String model, Color color, boolean spareTire, boolean convertible) {
        this.maxSpeed = maxSpeed;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.spareTire = spareTire;
        this.convertible = convertible;
    }

    public Car() {

    }

    @Override
    public void changeEngine() {
        this.maxSpeed += 40;
    }
}
