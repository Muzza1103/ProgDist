package com.example.rent.service;

import com.example.rent.model.Car;
import com.example.rent.repository.CarRepository;
import com.example.rent.repository.DateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.rent.model.Dates;
import java.util.List;

@RestController
public class RentalService {

    Logger logger = LoggerFactory.getLogger(RentalService.class);

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private DateRepository dateRepository;

    @GetMapping("/cars")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Car> listOfCars(){
        return carRepository.findAll();
    }


    @GetMapping("/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> aCar(@PathVariable("plateNumber") String plateNumber) throws Exception{
        Car car =carRepository.findByPlateNumber(plateNumber);
        if (car!=null) {
            logger.info("Car found: " + car.getPlateNumber());
            return new ResponseEntity<>(car, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("There is no car with this plate number in the database.", HttpStatus.OK);
        }
    }

    @PostMapping(value = "/cars/register/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> registerCar(
            @PathVariable("plateNumber") String plateNumber) throws Exception{
        Car car;
        if(carRepository.findByPlateNumber(plateNumber)!=null) {
            logger.info("Car already registered" + plateNumber);
            return new ResponseEntity<>("This car is already in the database.", HttpStatus.OK);
        }else {
            car = new Car(plateNumber);
            carRepository.save(car);
            logger.info("Car registered with plate number " + plateNumber);
            return new ResponseEntity<>("The car has been successfully registered.", HttpStatus.OK);
        }
    }

    @PutMapping(value = "/cars/return/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> returnCar(
            @PathVariable("plateNumber") String plateNumber,
            @RequestParam(value="rent", required = false)boolean rent) throws Exception{
        Car car;
        if(carRepository.findByPlateNumber(plateNumber)!=null) {
            car = carRepository.findByPlateNumber(plateNumber);
            car.setRented(rent);
            car.setDates(null);
            carRepository.save(car);
            carRepository.save(car);
            return new ResponseEntity<>("It has been registered that the car was returned.", HttpStatus.OK);
        }else {
            car = new Car(plateNumber);
            carRepository.save(car);
            return new ResponseEntity<>("The car wasn't in the database and was successfully added.", HttpStatus.OK);
        }
    }


    @PutMapping(value = "/cars/rent/{plateNumber}")
    public ResponseEntity<Object> rent(
            @PathVariable("plateNumber") String plateNumber,
            @RequestParam(value="rent", required = true)boolean rent,
            @RequestBody Dates dates){
        Car car = carRepository.findByPlateNumber(plateNumber);
        dateRepository.save(dates);
        if (car != null) {
            car.setRented(rent);
            car.setDates(dates);
        } else {
            car = new Car(plateNumber);
            car.setRented(rent);
            car.setDates(dates);
        }
        carRepository.save(car);
        return new ResponseEntity<>("It has been registered that the car will be rented for the specified period", HttpStatus.OK);

    }
}
