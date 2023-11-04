package com.nutech.service;


import com.nutech.model.entity.Service;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Service> getAllService() {
        return serviceRepository.findAll();
    }

}