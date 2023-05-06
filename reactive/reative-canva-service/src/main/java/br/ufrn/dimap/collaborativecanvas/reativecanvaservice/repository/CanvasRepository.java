package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.repository;

import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.Canvas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CanvasRepository extends JpaRepository<Canvas, Long> {

    Optional<Canvas> findByLink(String link);

    @Query(value = "SELECT * FROM Canvas e ORDER BY e.qtdPaintedPixels DESC LIMIT ?1", nativeQuery = true)
    Optional<List<Canvas>> findTopNByPaintedPixels(int limit);

}
