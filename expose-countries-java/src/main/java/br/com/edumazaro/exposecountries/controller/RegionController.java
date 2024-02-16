package br.com.edumazaro.exposecountries.controller;

import br.com.edumazaro.exposecountries.model.dto.CountryDTO;
import br.com.edumazaro.exposecountries.model.dto.PaginatedResponseDTO;
import br.com.edumazaro.exposecountries.model.entity.Region;
import br.com.edumazaro.exposecountries.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
    public ResponseEntity<List<Region>> findAll() {
        try {
            List<Region> regions = regionService.findAll();
            return ResponseEntity.ok(regions);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<PaginatedResponseDTO> findByRegionAndKeyWord(
            @RequestParam("region") String region,
            @RequestParam("keyWord") Integer keyWord,
            Pageable pageable) {
        try {
            Page<Region> regionsPage = regionService.findByRegionAndKeyWord(region, keyWord, pageable);

            List<CountryDTO> countryDTOs = regionsPage.getContent().stream()
                    .map(r -> new CountryDTO(r.getCountry(), r.getRegion(), r.getPopulation()))
                    .collect(Collectors.toList());

            PaginatedResponseDTO paginatedResponse = new PaginatedResponseDTO();
            paginatedResponse.setPage(regionsPage.getNumber());
            paginatedResponse.setPerPage(regionsPage.getSize());
            paginatedResponse.setTotal(regionsPage.getTotalElements());
            paginatedResponse.setTotalPages(regionsPage.getTotalPages());
            paginatedResponse.setData(countryDTOs);

            return ResponseEntity.ok(paginatedResponse);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
