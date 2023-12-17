package com.car.service.impl;

import com.car.model.Driver;
import com.car.repository.DriverRepository;
import com.car.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
    private DriverRepository driverRepository;
    @Autowired
    public  DriverServiceImpl(DriverRepository driverRepository){
        this.driverRepository=driverRepository;
    }

    @Override
    public List<Driver> findByAllDriver() {
        return driverRepository.findAll();
    }

    @Override
    public void SaveDriver(Driver driver) {
        this.driverRepository.save(driver);
    }

    @Override
    public Driver getDriverById(Long driver_Id) {
        Optional<Driver> optional=driverRepository.findById(driver_Id);
        Driver driver=null;
        if (optional.isPresent()){
            driver=optional.get();
        }else {
            throw new RuntimeException("teacher id not found "+driver_Id);
        }
        return driver;

    }

    @Override
    public void deleteDriverById(Long driver_Id) {
        this.driverRepository.deleteById(driver_Id);
    }
}
