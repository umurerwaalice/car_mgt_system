package com.car.controller;

import com.car.model.Driver;
import com.car.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DriverController {
    @Autowired
    private DriverService driverService;
    @GetMapping("/driverDashBoard")
    public String showDrivers(Model model){
        List<Driver> allDrivers = driverService.findByAllDriver();
        model.addAttribute("allDriver", allDrivers);
        return "driverDashBoard";
    }
    @GetMapping("/showNewDriverForm")
    public String showDriverForm(Model model){
        Driver driver = new Driver();
        model.addAttribute("driver", driver);
        return "driver";


    }
    @PostMapping("/saveDriver")
    public String saveDriver(@ModelAttribute("driver") Driver driver){
        driverService.SaveDriver(driver);
        return "redirect:/driverDashBoard";
    }
    @GetMapping("/updateDriver/{id}")
    public String updateDriver(@PathVariable(value = "id")Long driver_Id, Model model){
        Driver driver=driverService.getDriverById(driver_Id);
        model.addAttribute(driver);
        return "driverUpdate";
    }
    @GetMapping("/deleteDriver/{id}")
    public String deleteDriver(@PathVariable(value = "id") Long driver_Id){
        this.driverService.deleteDriverById(driver_Id);
        return "redirect:/driverDashBoard";
    }
}
