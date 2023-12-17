package com.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeContoller {
//    @GetMapping("/showList")
//    public String showList() {
//        return "allList";
//    }

    @GetMapping("/showHome")
    public String showHome() {
        return "index";
    }
    @GetMapping("/showTopbar")
    public String topNav() {
        return "topBar";
    }
    @GetMapping("/adminShowTopbar")
    public String adminTopNav() {
        return "adminTop";
    }
    @GetMapping("/showDashBoard")
    public String dashBoard() {
        return "dashBoard";
    }

    @GetMapping("/adminShowDashBoard")
    public String AdminDashBoard() {
        return "AdminDashboard";
    }

}
