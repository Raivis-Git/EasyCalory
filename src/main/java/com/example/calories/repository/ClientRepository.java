package com.example.calories.repository;

import com.example.calories.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT u FROM #{#entityName} u WHERE u.id =?1")
    Client findByClientId(Long client_id);
}
