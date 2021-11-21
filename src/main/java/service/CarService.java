package service;

import entity.Car;

import java.util.List;

public interface CarService {

    List<Car> findAll();

    Car save(Car car);

    void updateCarAgeJob();

    boolean isDone();
}
