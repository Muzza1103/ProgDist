package com.example.rent.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import com.example.rent.model.Dates;

@Entity
public class Car {

    @Id
    private String plateNumber;
    private Boolean rented = false;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dates_id", referencedColumnName = "id")
    private Dates dates;


    public Car(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Car(String plateNumber, Dates dates) {
        this.plateNumber = plateNumber;
        this.dates = dates;
    }

    public Car() {
    }

    public void setRented(Boolean rented) {
        this.rented = rented;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public Boolean getRented() {
        return rented;
    }

    public Dates getDates() {
        return dates;
    }

    @Override
    public String toString() {
        return "Car{" +
                "plateNumber='" + plateNumber + '\'' +
                ", rented=" + rented +
                ", dates=" + dates +
                '}';
    }

}
