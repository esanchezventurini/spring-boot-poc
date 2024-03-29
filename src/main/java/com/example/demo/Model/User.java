package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@PropertySource("classpath:application.properties")
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "users")
public class User extends BaseEntity{
    @Value("${adult.age}")
    @Transient
    private Integer adultAge;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @NotNull
    @Max(value = 120)
    @Min(value = 0)
    private Integer age;

    //Sets the name of the property that is going to be used by the JSON in the API.
    //@JsonProperty("emailAddress")
    @Email
    @NotNull
    private String email;

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    //The elements in the list will be saved when the object is saved
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private List<Vehicle> vehicles;

    //Defining the constructor attributes with @JsonProperty avoids creating an empty constructor.
    //Without that, the class cannot be instantiated from the API.
    public User(@JsonProperty("firstName") String firstName,
                @JsonProperty("lastName") String lastName,
                @JsonProperty("gender") Gender gender,
                @JsonProperty("age") Integer age,
                @JsonProperty("email") String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.vehicles = new ArrayList<>();
    }


    public User() {

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    //The property does not exist, but as it has a getter, it wil be returned as part of the JSON object.
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public int getYearOfBirth() {
        return LocalDateTime.now().minusYears(getAge()).getYear();
    }

    public void setAge(int age) {
        this.age = age;
    }

    //Does not show a property or getter as part of the JSON object.
    @JsonIgnore
    public boolean isAdult() {
        return age > adultAge;
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
    }
}
