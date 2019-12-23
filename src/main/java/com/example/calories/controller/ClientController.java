package com.example.calories.controller;

import com.example.calories.exception.ResourceNotFoundException;
import com.example.calories.model.Client;
import com.example.calories.process.P_Client;
import com.example.calories.repository.ClientRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public Page<Client> getClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @PostMapping("/clients")
    public Client createClient(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PostMapping("/clients/{clientId}")
    public Client updateClient(@PathVariable Long clientId,
                                   @Valid @RequestBody Client clientRequest) {
            return clientRepository.findById(clientId)
                    .map(client -> {
                        client.setActivity_level(clientRequest.getActivity_level());
                        client.setHeight(clientRequest.getHeight());
                        client.setWeight(clientRequest.getWeight());
                        return clientRepository.save(client);
                    }).orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + clientId));
    }

    @GetMapping("/client/{clientId}")
    public Client getSpecificClient(@PathVariable Long clientId) {
        if (clientRepository.existsById(clientId))
            return clientRepository.findById(clientId).get();
        else
            throw new ResourceNotFoundException("Client not found with id " + clientId);
    }

    @PostMapping("client/{clientId}/calculateCalories")
    public Integer countCalories(@PathVariable Long clientId) {
        return P_Client.getCalories(clientId);
    }

    @DeleteMapping("/clients/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Long clientId) {
        return clientRepository.findById(clientId)
                .map(client -> {
                    clientRepository.delete(client);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + clientId));
    }
}
