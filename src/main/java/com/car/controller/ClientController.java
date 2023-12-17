package com.car.controller;

import com.car.model.Client;
import com.car.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;
    @GetMapping("/clientDashBoard")
    public String showClients(Model model){
        List<Client> allClients = clientService.findByAllClient();
        model.addAttribute("allClient", allClients);
//        return "clientDashBoard";
        return "clientDashBoard";
    }
    @GetMapping("/showNewClientForm")
    public String showClientForm(Model model){
        Client client = new Client();
        model.addAttribute("client", client);
        return "client";

    }
    @PostMapping("/saveClient")
    public String saveClient(@ModelAttribute("client") Client client){
        clientService.SaveClient(client);
        return "redirect:/login";
    }
    @PostMapping("/saveClientAdmin")
    public String saveClientAdmin(@ModelAttribute("client") Client client){
        clientService.SaveClient(client);
        return "redirect:/clientDashBoard";
    }

    @GetMapping("/updateClient/{id}")
    public String updateClient(@PathVariable(value = "id")Long client_Id, Model model){
        Client client=clientService.getClientById(client_Id);
        model.addAttribute(client);
        return "clientUpdate";
    }
    @GetMapping("/deleteClient/{id}")
    public String deleteClient(@PathVariable(value = "id") Long client_Id){
        this.clientService.deleteClientById(client_Id);
        return "redirect:/clientDashBoard";
    }
}
