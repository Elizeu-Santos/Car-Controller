package com.example.demo.controller;

import java.util.ArrayList;                                       // Imports
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entities.Car;
import com.example.demo.entities.Category;
import com.example.demo.repositories.CarRepository;


@RestController
public class CarController {
    private CarRepository carRepository;   // Inserção do CarRepository
    private Long lastId;
    private List<Car> listCars;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;                          
        listCars = new ArrayList<>();
        lastId = 1L;
    }

    @PostMapping("/cars")                                        // POST Create new car
    public ResponseEntity<Car> create(@RequestBody Car car) {
        Car savedCar = carRepository.save(car);
        return ResponseEntity.ok(savedCar);
    }


    // Localhost:8080/cars?category=
    @GetMapping("/{id}")                                        // GET Listar todos os cars criado
    public ResponseEntity<List<Car>> findAll(
    @RequestParam(value = "category", required = false) Category category) {
        List<Car> cars;
        if (category != null) {
            cars = carRepository.findByCategory(category);
        } else {
            cars = carRepository.findAll();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/cars/{id}")                                  // GET por (ID) Cars
    public ResponseEntity<Car> find(@PathVariable Long id) {                                                                                              
        Optional<Car> optionalCar = carRepository.findById(id);
        if(optionalCar.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Car car = optionalCar.get();
        return ResponseEntity.ok(car);
    }

    @PutMapping("/cars/{id}")                                      // Update Car (ID)
    public ResponseEntity<Car> update(@PathVariable Long id,
                                        @RequestBody Car car) {
    Optional<Car> optionalCar = carRepository.findById(id);
    if (optionalCar.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    Car foundCar = optionalCar.get();
    if (car.getModel() != null) {
        foundCar.setModel(car.getModel());
    }
    if (car.getManufacturer() != null) {
        foundCar.setManufacturer(car.getManufacturer());
    }
    if (car.getYear() != null) {
        foundCar.setYear(car.getYear());
    }
    if (car.getCategory() != null) {
        foundCar.setCategory(car.getCategory());
    }

    carRepository.save(foundCar);
    return ResponseEntity.ok(foundCar);
    }

    @DeleteMapping("/car/{id}")                                   // Delete Car (ID)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if(optionalCar.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Car foundCar = optionalCar.get();
        carRepository.delete(foundCar);

        return ResponseEntity.noContent().build();
    }
}