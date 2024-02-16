package br.com.edumazaro.exposecountries.repositories;

import br.com.edumazaro.exposecountries.model.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    Page<Region> findByRegionAndKeyWord(String region, Integer keyWord, Pageable pageable);
}
