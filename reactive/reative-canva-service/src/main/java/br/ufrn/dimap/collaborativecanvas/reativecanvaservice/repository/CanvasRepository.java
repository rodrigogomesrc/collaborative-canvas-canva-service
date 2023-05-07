package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.repository;

import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.Canvas;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.List;
import java.util.Optional;

public interface CanvasRepository extends ReactiveCrudRepository<Canvas, Long> {

    Optional<Canvas> findByLink(String link);

    @Query("SELECT * FROM Canvas e ORDER BY e.qtdPaintedPixels DESC LIMIT ?1")
    Optional<List<Canvas>> findTopNByPaintedPixels(int limit);

}
