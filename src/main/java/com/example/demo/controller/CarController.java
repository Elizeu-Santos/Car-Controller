package com.example.demo.controller;

import java.util.ArrayList;                                       // Imports
import java.util.List;
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


@RestController
public class CarController {

    private Long lastId;
    private List<Car> listCars;

    public CarController() {                              // New ArrayList CarController
        listCars = new ArrayList<>();
        lastId = 1L;
    }

    @PostMapping("/car")                                        // POST Create new car
    public ResponseEntity<Car> create(@RequestBody Car car) {
        car.setId(lastId);
        lastId++;
        this.listCars.add(car);
        return ResponseEntity.ok(car);
    }


    // Localhost:8080/cars?category=
    @GetMapping("/cars")                                        // GET Listar todos os cars criado
    public ResponseEntity<List<Car>> findAll(
    @RequestParam(value = "category", required = true) Category category) {
        if (category != null) {
            List<Car> categoryCars = new ArrayList<>();
            for (int i = 0; i < listCars.size(); i++) {
                if (listCars.get(i).getCategory().equals(category)) {
                    categoryCars.add(listCars.get(i));
                }
            }

            return ResponseEntity.ok(categoryCars);
        }
        
        return ResponseEntity.ok(listCars);
    }

    @GetMapping("/cars/{id}")                                  // GET por (ID) Cars
    public ResponseEntity<Car> find(@PathVariable Long id) {
                                                                /* Optional <Car> ca = listCars 
                                                                 .stream()
                                                                 filter(c -> c.get/id() == id)
                                                                 .findFirst();
                                                                */                                                     
        Car car;
        for(int i = 0; i < listCars.size(); i++) {              
            car = listCars.get(i);
            if (car.getId() == id) {
                return ResponseEntity.ok(car);
            }
        }

        return ResponseEntity.notFound().build();
    }


    @PutMapping("/car/{id}")                                      // Update Car (ID)
    public ResponseEntity<Car> update(@PathVariable Long id,
                                        @RequestBody Car car) {
    car.setId(id);
    for(int i = 0; i < listCars.size(); i++) {
        if (listCars.get(i).getId().equals(car.getId())) {

                                                            // Substitui carro do indice i
            listCars.set(i,car);

            return ResponseEntity.ok(car);
        }
    }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/car/{id}")                                   // Delete Car (ID)
    public ResponseEntity<?> delete(@PathVariable Long id) {

        for(int i = 0; i < listCars.size(); i++) {
            if (listCars.get(i).getId().equals(id)) {
                listCars.remove(i);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.notFound().build();
    }
}