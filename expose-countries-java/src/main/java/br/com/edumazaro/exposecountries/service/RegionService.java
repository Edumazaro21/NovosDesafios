package br.com.edumazaro.exposecountries.service;

import br.com.edumazaro.exposecountries.model.entity.Region;
import br.com.edumazaro.exposecountries.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Page<Region> findByRegionAndKeyWord(String region, Integer keyWord, Pageable pageable) {
        return regionRepository.findByRegionAndKeyWord(region, keyWord, pageable);
    }
}
