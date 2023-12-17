package com.car.service;

import com.car.model.Car;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface CarService {
    void saveCar(Car car);
//Car saveCar(String plateNumber,String name, String manufacture,Long year ,Long price ,Long seat,Long driver,byte[] image);
    List<Car> listAllCarsCars();
    Car getCarById(Long plateNumber);
    void deleteCarById(Long plateNumber);
    Optional<Car> getCarByIdImage(Long id);

List<Car>findCarByKeyword(String keyword);

//used to handle downlaod
Car findCarById(Long carId);

}
