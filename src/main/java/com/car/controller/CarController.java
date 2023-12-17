package com.car.controller;

import com.car.model.Car;
import com.car.model.Client;
import com.car.model.Driver;
import com.car.service.CarService;
import com.car.service.ClientService;
import com.car.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private DriverService driverService;


    @GetMapping("/showNewCarForm")
    public String showCarForm(Model model){
        Car car= new Car();
        List<Driver> driver=driverService.findByAllDriver();
        model.addAttribute("listDriver",driver);
        model.addAttribute("car",car);

        return "car";
    }
    @PostMapping("/saveCar")
    public String saveCar(@ModelAttribute("car") Car car,@RequestParam("imageFile") MultipartFile imageFile){
        try {
            if (!imageFile.isEmpty()) {
                byte[] imageBytes = imageFile.getBytes();
                car.setImage(imageBytes);
            }
        }catch (IOException e) {
            // Handle the exception appropriately
            e.printStackTrace();
            return "redirect:/adminViewCar"; // Replace with your error page
        }

        carService.saveCar(car);
       return "redirect:/adminViewCar";
    }
    @GetMapping("/carDashBoard")
    public String showAllCar( Model model){
        List<Car> cars=carService.listAllCarsCars();

        model.addAttribute("allcars",cars);
        return "carDashBoard";
    }
    @GetMapping("/carView")
    public String carViewForClient( Model model,String keyword){
        List<Car> cars=carService.listAllCarsCars();
        model.addAttribute("allcars",cars);
        if(keyword!=null){
            model.addAttribute("allcars",carService.findCarByKeyword(keyword));
        }else {
            model.addAttribute("allcars", cars);
        }
        return "carViewForClient";
    }
//    @GetMapping("/carViewBooking")
//    public String clientViewCarBooking( @RequestParam Long clientId, Model model){
//        List<Car> cars=carService.listAllCarsCars();
//        Client client=clientService.getClientById(clientId);
//        String clientName=client.getUsername();
//        model.addAttribute("clientName",clientName);
//        model.addAttribute(clientId);
//        model.addAttribute("allcars",cars);
//        return "ClientViewCarBooking";
//    }
    @GetMapping("/adminViewCar")
    public String adminViewCar( Model model,String keyword) {
        List<Car> cars = carService.listAllCarsCars();
        String imageData = null;
        for (Car car : cars) {
            byte[] imageBytes = car.getImage();
            imageData = Base64.getEncoder().encodeToString(imageBytes);
            car.setImageData(imageData);

        }
        if(keyword!=null){
            model.addAttribute("allcars",carService.findCarByKeyword(keyword));
        }else {
            model.addAttribute("carImage", imageData);
            model.addAttribute("allcars", cars);
        }
        return "adminViewCar";
    }
    @GetMapping("/updateCar/{id}")
    public String updateCar(@PathVariable(value = "id")Long id, Model model){
        Car car=carService.getCarById(id);
        List<Driver> driver=driverService.findByAllDriver();
        model.addAttribute("listDriver",driver);
        model.addAttribute(car);
        return "carUpdate";
    }
    @GetMapping("/deleteCar/{id}")
    public String deleteCar(@PathVariable(value = "id") Long id){
        this.carService.deleteCarById(id);
        return "redirect:/adminViewCar";

    }
//    used to view Image
    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long id) {
        Optional<Car>image=carService.getCarByIdImage(id);
        if(image.isPresent()){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image.get().getImage());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
//used to download image
//    @GetMapping("/images/{carId}")
@GetMapping("/downloadImage/{carId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long carId) {
        Car car = carService.getCarById(carId);

        if (car == null || car.getImage() == null) {
            return ResponseEntity.notFound().build();
        }

        // Create a ByteArrayResource from the car image
        ByteArrayResource resource = new ByteArrayResource(car.getImage());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=car_image.jpg")
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }

}
