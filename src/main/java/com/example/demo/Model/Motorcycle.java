package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.awt.*;

@DiscriminatorValue("MOTORCYCLE")
@Entity
public class Motorcycle extends Vehicle{
    @JsonProperty("noiseLevel")
    private int noiseLevel;

    public Motorcycle(int maxSpeed, Brand brand, String model, Color color, int noiseLevel) {
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
