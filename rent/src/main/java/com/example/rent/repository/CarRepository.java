package com.example.rent.repository;

import com.example.rent.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    public Car findByPlateNumber(String plateNumber);

    public List<Car> findAll();

}
