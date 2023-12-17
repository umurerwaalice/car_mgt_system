package com.car.service.impl;

import com.car.model.Car;
import com.car.repository.CarRepository;
import com.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;
    @Autowired
    public CarServiceImpl (CarRepository carRepository){

        this.carRepository=carRepository;
    }

    @Override
    public void saveCar(Car car) {

        this.carRepository.save(car);
    }


    @Override
    public List<Car> listAllCarsCars() {

        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        Optional<Car> optional=carRepository.findById(id);
        Car car=null;
        if (optional.isPresent()){
            car=optional.get();
        }else {
            throw new RuntimeException("teacher id not found "+id);
        }
        return car;

    }

    @Override
    public void deleteCarById(Long id) {
        this.carRepository.deleteById(id);
    }

    @Override
    public Optional<Car> getCarByIdImage(Long id) {

        return this.carRepository.findById(id);
    }

    @Override
    public List<Car> findCarByKeyword(String keyword) {
        return this.carRepository.findByKeyword(keyword);
    }

    @Override
    public Car findCarById(Long carId) {
        return carRepository.findById(carId).orElse(null);
    }


}
