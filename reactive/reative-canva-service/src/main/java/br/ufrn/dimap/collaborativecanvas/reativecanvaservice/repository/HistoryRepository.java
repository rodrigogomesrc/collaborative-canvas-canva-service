package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.repository;

import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query(value = "SELECT * FROM History e WHERE e.canvas_id = ?1 ORDER BY e.id DESC LIMIT ?2", nativeQuery = true)
    Optional<List<History>> findLastNHistories(Long canvasId, Integer n);
}

