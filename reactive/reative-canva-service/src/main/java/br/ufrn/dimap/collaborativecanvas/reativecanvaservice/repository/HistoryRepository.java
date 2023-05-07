package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.repository;

import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.History;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface HistoryRepository extends ReactiveCrudRepository<History, Long> {
    @Query("SELECT * FROM history WHERE canvas_id = :canvasId ORDER BY id DESC LIMIT :n")
    Flux<History> findLastNHistories(Long canvasId, Integer n);
}
