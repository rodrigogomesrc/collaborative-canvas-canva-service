package br.ufrn.dimap.collaborativecanvas.canvaservice.Repository;

import br.ufrn.dimap.collaborativecanvas.canvaservice.model.Canvas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CanvasRepository extends JpaRepository<Canvas, Long> {

    Optional<Canvas> findByLink(String link);

}
