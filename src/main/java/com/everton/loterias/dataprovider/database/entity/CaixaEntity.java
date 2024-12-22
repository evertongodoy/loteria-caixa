package com.everton.loterias.dataprovider.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sorteios")
public class CaixaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;
    @Column(nullable = false)
    private Integer numero;
    @Column(nullable = false)
    private String tipoJogo;
    @Column(nullable = false)
    private String dezenas;
    @Column(nullable = false)
    private LocalDate apuracao;
    @Column(nullable = false)
    private String municipio;

}
