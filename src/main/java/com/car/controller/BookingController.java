package com.car.controller;

import com.car.model.Booking;
import com.car.model.Car;
import com.car.model.Client;
import com.car.service.BookingService;
import com.car.service.CarService;
import com.car.service.ClientService;
import com.car.service.EmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookingController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CarService carService;

    @GetMapping("/bookingDashBoard")
    public String showBookings(Model model){
        List<Booking> allBookings = bookingService.findByAllBooking();
        model.addAttribute("allBooking", allBookings);
        return "bookingDashBoard";
    }
    @GetMapping("/showNewBookingForm")
    public String showBookingForm(@RequestParam Long clientId, @RequestParam Long carId, Model model){
        Booking booking = new Booking();
        Client client=clientService.getClientById(clientId);
        Car car=carService.getCarById(carId);
        String clientName = client.getName();
        model.addAttribute("clientName", clientName);
        model.addAttribute("booking", booking);
        model.addAttribute("client", client);
        model.addAttribute("car", car);
        return "booking";
    }
    @PostMapping("/saveBooking")
    public String saveBooking(@ModelAttribute("booking") Booking booking, HttpSession session,Model model){
        bookingService.SaveBooking(booking);
        Long clientId = (Long) session.getAttribute("clientId");
        Client name = clientService.getClientById(clientId);
        model.addAttribute("clientId", clientId);
        return "redirect:/carViewBooking";
    }
    @PostMapping("/saveBookingAdmin")
    public String saveBookingAdmin(@ModelAttribute("booking") Booking booking, HttpSession session,Model model){
        bookingService.SaveBooking(booking);
//        Long clientId = (Long) session.getAttribute("clientId");
//        Client name = clientService.getClientById(clientId);
//        model.addAttribute("clientId", clientId);
        return "redirect:/bookingDashBoard";
    }
    @GetMapping("/updateBooking/{id}")
    public String updateBooking(@PathVariable(value = "id")Long booking_Id, Model model){
        Booking booking=bookingService.getBookingById(booking_Id);
        model.addAttribute(booking);
        return "bookingUpdate";
    }
//    @GetMapping("/deleteBooking/{id}")
//    public String deleteBooking(@PathVariable(value = "id") Long booking_Id){
//        this.bookingService.deleteBookingById(booking_Id);
//        return "redirect:/bookingDashBoard";
//    }

@GetMapping("/approveBooking/{id}")
@ResponseBody
public String approveBooking(@PathVariable(value = "id") Long booking_Id) {
    Booking booking = bookingService.getBookingById(booking_Id);
    Client client = booking.getClient();
    String clientEmail = client.getEmail();

    // Send email to the client
    String subject = "Booking Approved";
    String text = "Your booking has been approved. Thank you!";
    emailService.sendEmail(clientEmail, subject, text);

    // Perform any other necessary actions for approving the booking

    return "Booking approved and email sent.";
}
    @GetMapping("/denyBooking/{id}")
//    @ResponseBody
    public String denyBooking(@PathVariable(value = "id") Long booking_Id) {
        Booking booking = bookingService.getBookingById(booking_Id);
        Client client = booking.getClient();
        String clientEmail = client.getEmail();

        // Send email to the client
        String subject = "Booking Denied";
        String text = "Your booking has been denied.";
        emailService.sendEmail(clientEmail, subject, text);

        // Delete the booking
        bookingService.deleteBookingById(booking_Id);

//        return "Booking denied and email sent.";
        return "redirect:/bookingDashBoard";
    }
    @GetMapping("/bookedCarDashBoard")
    public String showBookedCars(Model model) {
        List<Booking> approvedBookings = bookingService.findByAllBooking().stream()
                .filter(booking -> booking.getStatus().equals("Approve"))
                .collect(Collectors.toList());
        model.addAttribute("approvedBookings", approvedBookings);
        return "BookedDashboard";
    }

}
