package br.com.edumazaro.exposecountries.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginatedResponseDTO {
    private int page;
    private int perPage;
    private long total;
    private int totalPages;
    private List<CountryDTO> data;
}
