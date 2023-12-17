package com.car.service;

import com.car.model.Driver;

import java.util.List;

public interface DriverService {
    List<Driver> findByAllDriver();
    void SaveDriver(Driver driver);
    Driver getDriverById(Long driver_Id);
    void deleteDriverById(Long driver_Id);

}
