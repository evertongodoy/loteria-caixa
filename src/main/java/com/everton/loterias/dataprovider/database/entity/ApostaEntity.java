package com.everton.loterias.dataprovider.database.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "apostas")
public class LoteriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;
    @Column(nullable = false)
    private String tipoJogo;
    @Column(nullable = false)
    private String numeros;
    @Column(nullable = false)
    private LocalDate inicio;
    @Column(nullable = false)
    private boolean ativo;

}
