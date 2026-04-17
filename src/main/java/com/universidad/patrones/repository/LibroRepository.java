package com.universidad.patrones.repository;

import com.universidad.patrones.model.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    boolean existsByIsbn(String isbn);

    @Query("SELECT l FROM Libro l WHERE l.titulo LIKE %:palabra%")
    List<Libro> buscarPorPalabra(@Param("palabra") String palabra);
}
