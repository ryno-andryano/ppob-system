package com.nutech.service;

import com.nutech.model.entity.Banner;
import com.nutech.repository.BannerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerService {

    private final BannerRepository bannerRepository;

    public BannerService(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    public List<Banner> getAllBanner() {
        return bannerRepository.findAll();
    }

}
