package br.ufrn.dimap.collaborativecanvas.canvaservice.repository;

import br.ufrn.dimap.collaborativecanvas.canvaservice.model.Pixel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PixelRepository extends JpaRepository<Pixel, Long> {

}
