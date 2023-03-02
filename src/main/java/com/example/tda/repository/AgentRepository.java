package com.example.tda.repository;

import com.example.tda.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Integer> {
    Agent findByEmail(String email);
}