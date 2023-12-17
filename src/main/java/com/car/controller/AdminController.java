package com.car.controller;

import com.car.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    @Autowired
    AdminService adminService;
    @PostMapping("/loginAdmin")
    public String loginForAdmin(@RequestParam String username, @RequestParam String password, Model model) {
        if (adminService.authenticateAdmin(username,password)) {
            // Client authentication successful, redirect to another page
            return "redirect:/adminViewCar";
        } else {
            // Authentication failed, add an error message to the model
            model.addAttribute("error", "Username and password do not match");
            return "adminLogin";
        }
    }
}
