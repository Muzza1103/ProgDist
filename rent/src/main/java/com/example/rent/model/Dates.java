package com.example.rent.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Dates {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String startDate;
    String endDate;

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Dates{" +
                "endDate='" + endDate + '\'' +
                ", startDate='" + startDate + '\'' +
                '}';
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}