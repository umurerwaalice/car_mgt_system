package com.car.service.impl;

import com.car.model.Client;
import com.car.repository.ClientRepository;
import com.car.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    @Autowired
    public ClientServiceImpl (ClientRepository clientRepository){
        this.clientRepository=clientRepository;
    }

    @Override
    public List<Client> findByAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public void SaveClient(Client client) {
    this.clientRepository.save(client);
    }

    @Override
    public Client getClientById(Long client_Id) {
        Optional<Client> optional=clientRepository.findById(client_Id);
        Client client=null;
        if (optional.isPresent()){
            client=optional.get();
        }else {
            throw new RuntimeException("teacher id not found "+client_Id);
        }
        return client;

    }

    @Override
    public void deleteClientById(Long client_Id) {
    this.clientRepository.deleteById(client_Id);
    }

    @Override
    public boolean authenticateClient(String username, String password) {
        Client client = clientRepository.findByUsernameAndPassword(username, password);
        return client != null;

    }

    @Override
    public Client getClientByUsername(String username) {
        return clientRepository.findByUsername(username);
    }


}
