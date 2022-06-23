package com.example.demo.Model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.awt.*;

@DiscriminatorValue("CAR")
@Entity
public class Car extends Vehicle{
    private boolean spareTire;
    private boolean convertible;

    public Car(int maxSpeed, String brand, String model, Color color, boolean spareTire, boolean convertible) {
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
