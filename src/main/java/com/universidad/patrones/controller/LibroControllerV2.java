package com.universidad.patrones.controller;

import com.universidad.patrones.dto.LibroRequestDTO;
import com.universidad.patrones.dto.LibroResponseDTO;
import com.universidad.patrones.mapper.LibroMapper;
import com.universidad.patrones.model.Libro;
import com.universidad.patrones.service.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/libros")
@Tag(name = "Libros", description = "Operaciones CRUD sobre el catalogo de libros")
public class LibroControllerV2 {

    private final LibroService service;
    private final LibroMapper mapper;

    public LibroControllerV2(LibroService service, LibroMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    @Operation(summary = "Listar todos los libros")
    public List<LibroResponseDTO> listar() {
        return service.findAll().stream().map(mapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener libro por ID")
    public ResponseEntity<LibroResponseDTO> obtener(@PathVariable Long id) {
        Libro libro = service.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Libro no encontrado: " + id));
        return ResponseEntity.ok(mapper.toResponse(libro));
    }

    @PostMapping
    @Operation(summary = "Crear nuevo libro")
    public ResponseEntity<LibroResponseDTO> crear(@RequestBody @Valid LibroRequestDTO dto) {
        Libro guardado = service.save(mapper.toEntity(dto));
        return ResponseEntity.status(201).body(mapper.toResponse(guardado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar libro por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
