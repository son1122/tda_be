package com.example.tda.Controller;

import com.example.tda.entity.Agent;
import com.example.tda.repository.AgentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class RegisterController {
    private final AgentRepository agentRepository;

    public RegisterController(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }


    @PostMapping("/agent/register")
    public ResponseEntity<String> register(@RequestBody Agent agent) {
        agentRepository.save(agent);
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }

    @PostMapping("/agent/login")
    public ResponseEntity<Agent> login(@RequestParam String email, @RequestParam String password) {
        Optional<Agent> agent = Optional.ofNullable(agentRepository.findByEmail(email));
        if (agent.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(agent.get(), HttpStatus.OK);
        }

    }

    @GetMapping("/agent/profile")
    public ResponseEntity<Agent> profile(@RequestParam Integer id){

        Agent agent = agentRepository.findById(id).get();

        return new ResponseEntity<>(agent,HttpStatus.OK);
    }





}