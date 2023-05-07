package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.repository;

import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.Pixel;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PixelRepository extends ReactiveCrudRepository<Pixel, Long> {

    Flux<Pixel> findAllByCanvasId(Long canvasId);

}
