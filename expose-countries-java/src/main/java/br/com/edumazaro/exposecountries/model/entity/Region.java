package br.com.edumazaro.exposecountries.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "region")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="region", length=80, nullable=false)
    private String region;

    @Column(name="key_word", nullable=false)
    private Integer keyWord;

    @Column(name = "country", length = 80, nullable = false)
    private String country;

    @Column(name = "population", nullable = false)
    private Long population;
}
