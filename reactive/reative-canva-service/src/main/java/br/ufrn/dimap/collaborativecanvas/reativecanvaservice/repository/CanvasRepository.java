package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.repository;

import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.Canvas;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface CanvasRepository extends ReactiveCrudRepository<Canvas, Long> {

    Mono<Canvas> findByLink(String link);

    @Query("SELECT * FROM Canvas e ORDER BY e.qtdPaintedPixels DESC LIMIT ?1")
    Flux<Canvas> findTopNByPaintedPixels(int limit);

}
