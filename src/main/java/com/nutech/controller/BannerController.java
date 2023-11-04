package com.nutech.controller;

import com.nutech.model.dto.ResponseTemplate;
import com.nutech.model.entity.Banner;
import com.nutech.service.BannerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BannerController {

    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping("/banner")
    public ResponseEntity<ResponseTemplate<List<Banner>>> getAllBanner() {
        List<Banner> data = bannerService.getAllBanner();
        ResponseTemplate<List<Banner>> response = new ResponseTemplate<>(0, "Sukses", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
