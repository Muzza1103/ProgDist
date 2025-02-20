package com.example.rent.repository;

import com.example.rent.model.Car;
import com.example.rent.model.Dates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DateRepository extends JpaRepository<Dates, Long> {


}