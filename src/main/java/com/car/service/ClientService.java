package com.car.service;

import com.car.model.Client;

import java.util.List;

public interface ClientService {
    List<Client> findByAllClient();
    void SaveClient(Client driver);
    Client getClientById(Long client_Id);
    void deleteClientById(Long client_Id);
     boolean authenticateClient(String username, String password);
    Client getClientByUsername(String username);

}
