package com.example.tda.Controller;


import com.example.tda.entity.Customer;
import com.example.tda.entity.Order;
import com.example.tda.entity.Package;
import com.example.tda.repository.AgentRepository;
import com.example.tda.repository.OrderRepository;
import com.example.tda.repository.PackageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PackageController {
    private final PackageRepository packageRepository;
    private final AgentRepository agentRepository;
    private final OrderRepository orderRepository;

    public PackageController(PackageRepository packageRepository,
                             AgentRepository agentRepository,
                             OrderRepository orderRepository) {
        this.packageRepository = packageRepository;
        this.agentRepository = agentRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/packages")
    public ResponseEntity<List> getPackage(){
        List<Package> packages = packageRepository.findAll();
        return new ResponseEntity<>(packages, HttpStatus.OK);
    }
    @PostMapping("/buy")
    public  ResponseEntity<Order> buyPackage(@RequestParam Integer agentId, @RequestParam Integer packageId, @RequestParam Customer customerId){
        Order order = new Order();
        order.setOrderPackage(packageRepository.findById(packageId).get());
        order.setAgent(agentRepository.findById(agentId).get());
        order.setOrderPackage(packageRepository.findById(packageId).get());
        orderRepository.save(order);
        return new ResponseEntity<>(null,HttpStatus.CREATED);
    }




}
