package com.example.calories.controller;

import com.example.calories.exception.ResourceNotFoundException;
import com.example.calories.model.Client;
import com.example.calories.model.Recipe;
import com.example.calories.model.RecipeHistory;
import com.example.calories.model.json.JSONCalories;
import com.example.calories.process.P_Client;
import com.example.calories.repository.ClientRepository;
import com.example.calories.repository.RecipeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RecipeHistoryRepository recipeHistoryRepository;

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
                        client.setActivity_level(clientRequest.getActivity_level() == null ? client.getActivity_level() : clientRequest.getActivity_level());
                        client.setHeight(clientRequest.getHeight() == null ? client.getHeight() : clientRequest.getHeight());
                        client.setWeight(clientRequest.getWeight() == null ? client.getWeight() : clientRequest.getWeight());
                        client.setEmail(clientRequest.getEmail() == null ? client.getEmail() : clientRequest.getEmail());
                        client.setMeat_eater(clientRequest.getMeat_eater() == null ? client.getMeat_eater() : clientRequest.getMeat_eater());
                        client.setRecipeHistoryId(clientRequest.getRecipeHistoryId() == null? client.getRecipeHistoryId() : clientRequest.getRecipeHistoryId());
                        return clientRepository.save(client);
                    }).orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + clientId));
    }

    @GetMapping("/client/{clientId}")
    public Client getSpecificClient(@PathVariable Long clientId) {
        if (clientRepository.existsById(clientId))
            return clientRepository.findByClientId(clientId);
        else
            throw new ResourceNotFoundException("Client not found with id " + clientId);
    }

    @GetMapping("client/{clientId}/calculateCalories")
    public JSONCalories countCalories(@PathVariable Long clientId) throws Exception {
        Client client = clientRepository.findByClientId(clientId);
        return P_Client.getCalories(client);
    }

    @DeleteMapping("/clients/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Long clientId) {
        return clientRepository.findById(clientId)
                .map(client -> {
                    clientRepository.delete(client);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + clientId));
    }

    @GetMapping("/clients/{clientId}/history")
    public List<Recipe> getRecipeHistory(@PathVariable Long clientId) {
        List<RecipeHistory> recipeHistoryList = recipeHistoryRepository.findAllByClientId(clientRepository.findByClientId(clientId));
        List<Recipe> recipeList = new ArrayList<>();
        for (RecipeHistory recipeHistory : recipeHistoryList) {
            recipeList.add(recipeHistory.getRecipeId());
        }
        return recipeList;
    }
}
