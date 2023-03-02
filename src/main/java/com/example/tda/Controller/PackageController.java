package com.example.tda.Controller;


import com.example.tda.entity.Order;
import com.example.tda.entity.Package;
import com.example.tda.repository.PackageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PackageController {
    private final PackageRepository packageRepository;

    public PackageController(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @GetMapping("/packages")
    public ResponseEntity<List> getPackage(){
        List<Package> packages = packageRepository.findAll();
        return new ResponseEntity<>(packages, HttpStatus.OK);
    }
    @PostMapping("/buy")
    public  ResponseEntity<Order> buyPackage(){

        return new ResponseEntity<>(null,HttpStatus.CREATED);
    }


}
