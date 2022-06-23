package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import java.awt.*;

@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Car.class, name = "car"),
        @JsonSubTypes.Type(value = Motorcycle.class, name = "motorcycle")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "vehicles")
public abstract class Vehicle {
    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("maxSpeed")
    protected int maxSpeed;

    @Enumerated(EnumType.STRING)
    @JsonProperty("brand")
    protected Brand brand;

    @JsonProperty("model")
    protected String model;

    @Enumerated(EnumType.STRING)
    @JsonProperty("color")
    protected Color color;

    public Long getId() {
        return id;
    }

    public abstract void changeEngine();
}
