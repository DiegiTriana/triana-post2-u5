package com.universidad.patrones.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "libros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

    @Column(nullable = false)
    @NotBlank(message = "El autor es obligatorio")
    private String autor;

    @Column(unique = true)
    @NotBlank(message = "El ISBN es obligatorio")
    private String isbn;

    @Min(value = 1800, message = "El anio debe ser valido")
    private Integer anioPublicacion;

    private String categoria;
}
