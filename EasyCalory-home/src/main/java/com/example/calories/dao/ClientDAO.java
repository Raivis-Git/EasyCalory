package com.example.calories.dao;

import com.example.calories.model.Client;
import com.example.calories.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientDAO {

    @Autowired
    private ClientRepository clientRepository;

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Client findByClientId(Long clientId) {
        return clientRepository.findByClientId(clientId);
    }
}
