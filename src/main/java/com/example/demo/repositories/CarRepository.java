package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Car;
import com.example.demo.entities.Category;


@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findByCategory (Category category);
}