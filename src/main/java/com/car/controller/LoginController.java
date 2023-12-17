package com.car.controller;

import com.car.model.Booking;
import com.car.model.Car;
import com.car.model.Client;
import com.car.repository.BookingRepository;
import com.car.service.BookingService;
import com.car.service.CarService;
import com.car.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LoginController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private CarService carService;
    @Autowired
    private BookingService bookingService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("client", new Client());
        return "login";
    }
//    @GetMapping("/logout")
//    public String showLogoutForm(Model model) {
//        model.addAttribute("client", new Client());
//        return "login";
//    }
    @GetMapping("/loginAdmin")
    public String showLoginFormForAdmin(Model model) {
        model.addAttribute("client", new Client());
        return "adminLogin";
    }

    @GetMapping("/CarForm")
    public String showCarForm(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "car";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        if (clientService.authenticateClient(username, password)) {
            Client client = clientService.getClientByUsername(username);
            Long clientId = client.getClient_Id();
            session.setAttribute("clientId", clientId);
            return "redirect:/carViewBooking";
        } else {
            model.addAttribute("error", "Username and password do not match");
            return "login";
        }
    }
        @GetMapping("/carViewBooking")
        public String showCarViewBooking(Model model, HttpSession session ,String keyword) {
            List<Car> cars = carService.listAllCarsCars();
            model.addAttribute("allcars", cars);
            Long clientId = (Long) session.getAttribute("clientId");
            Client name = clientService.getClientById(clientId);
            String clientName = name.getName();
            model.addAttribute("clientName", clientName);
            // Use the clientId as needed
            model.addAttribute("clientId", clientId);
            // Add other attributes and logic for the carViewBooking page
            if(keyword!=null){
                model.addAttribute("allcars",carService.findCarByKeyword(keyword));
            }else {
                model.addAttribute("allcars", cars);
            }

            return "ClientViewCarBooking";
        }
    @GetMapping("/clientTopBar")
    public String showClientTopBar(Model model, HttpSession session) {
        Long clientId = (Long) session.getAttribute("clientId");
        // Use the clientId as needed
        model.addAttribute("clientId", clientId);
        // Add other attributes and logic for the clientTopBar page
        return "clientTopBar";
    }
//    @GetMapping("/carBookedByClient")
//    public String carBookedByClient(Model model, HttpSession session) {
//        Long clientId = (Long) session.getAttribute("clientId");
//        List<Booking> bookedCarByClient=bookingService.findByAllBooking();
//        model.addAttribute("booked",bookedCarByClient);
//        // Use the clientId as needed
//        model.addAttribute("clientId", clientId);
//        // Add other attributes and logic for the clientTopBar page
//        return "carBookedByClient";
//    }
@GetMapping("/carBookedByClient")
public String carBookedByClient(Model model, HttpSession session) {
    Long clientId = (Long) session.getAttribute("clientId");
    List<Booking> bookedCarByClient = bookingService.findByAllBooking();
    Client name = clientService.getClientById(clientId);
    String clientName = name.getName();
    model.addAttribute("clientName", clientName);
    // Filter the list to include only bookings with status "Approve" and matching clientId
    List<Booking> approvedBookings = bookedCarByClient.stream()
            .filter(booking -> booking.getStatus().equals("Approve") && booking.getClient().getClient_Id().equals(clientId))
            .collect(Collectors.toList());

    model.addAttribute("booked", approvedBookings);
    model.addAttribute("clientId", clientId);

    // Add other attributes and logic for the clientTopBar page

    return "carBookedByClient";
}
    @GetMapping("/clientAccountInfo")
    public String clientAccountInfo(Model model, HttpSession session) {
        Long clientId = (Long) session.getAttribute("clientId");
       Client clientInfo =clientService.getClientById(clientId);
        Client name = clientService.getClientById(clientId);
        String clientName = name.getName();
        model.addAttribute("clientName", clientName);
        // Use the clientId as needed
        model.addAttribute("clientInfo",clientInfo);
        // Add other attributes and logic for the clientTopBar page
        return "clientAccountInfo";
    }
    @GetMapping("/clientAccountInfoUpdate")
    public String clientAccountInfoUpdate(Model model, HttpSession session) {
        Long clientId = (Long) session.getAttribute("clientId");
        Client clientInfo1 =clientService.getClientById(clientId);
        Client name = clientService.getClientById(clientId);
        String clientName = name.getName();
        model.addAttribute("clientName", clientName);
        // Use the clientId as needed
        model.addAttribute("clientInf1",clientInfo1);
        // Add other attributes and logic for the clientTopBar page
        return "clientAccountInfoUpdate";
    }
    @PostMapping("/saveClientInfo")
    public String saveClientInfo(@ModelAttribute("client") Client client, HttpSession session,Model model){
        clientService.SaveClient(client);
        Long clientId = (Long) session.getAttribute("clientId");
        Client clientInfo =clientService.getClientById(clientId);
        Client name = clientService.getClientById(clientId);
        String clientName = name.getName();
        model.addAttribute("clientName", clientName);
        // Use the clientId as needed
        model.addAttribute("clientInfo",clientInfo);
        return "redirect:/clientAccountInfo";
    }
    }

