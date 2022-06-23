package com.example.demo.Model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.awt.*;

@DiscriminatorValue("MOTORCYCLE")
@Entity
public class Motorcycle extends Vehicle{
    private int noiseLevel;

    public Motorcycle(int maxSpeed, String brand, String model, Color color, int noiseLevel) {
        this.maxSpeed = maxSpeed;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.noiseLevel = noiseLevel;
    }

    public Motorcycle() {

    }

    @Override
    public void changeEngine() {
        this.maxSpeed -= 10;
    }
}
