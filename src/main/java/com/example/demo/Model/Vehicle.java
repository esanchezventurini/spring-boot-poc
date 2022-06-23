package com.example.demo.Model;

import javax.persistence.*;
import java.awt.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Vehicle {
    @Id
    @GeneratedValue
    private Long id;

    protected int maxSpeed;
    protected String brand;
    protected String model;
    protected Color color;

    public Long getId() {
        return id;
    }

    public abstract void changeEngine();
}
