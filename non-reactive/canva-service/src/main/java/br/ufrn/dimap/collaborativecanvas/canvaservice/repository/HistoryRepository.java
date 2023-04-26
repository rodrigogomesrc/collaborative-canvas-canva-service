package br.ufrn.dimap.collaborativecanvas.canvaservice.repository;

import br.ufrn.dimap.collaborativecanvas.canvaservice.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {

}
