package com.nutech.controller;

import com.nutech.model.dto.ResponseTemplate;
import com.nutech.model.entity.Service;
import com.nutech.service.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/services")
    public ResponseEntity<ResponseTemplate<List<Service>>> getAllService() {
        List<Service> data = serviceService.getAllService();
        ResponseTemplate<List<Service>> response = new ResponseTemplate<>(0, "Sukses", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
