package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.service;

import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.Pixel;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.repository.PixelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class PixelService {

    PixelRepository pixelRepository;
    public PixelService(@Autowired PixelRepository pixelRepository) {
        this.pixelRepository = pixelRepository;
    }

    public Flux<Pixel> getPixelsFromCanvas(Long canvasId) {
        return pixelRepository.findAllByCanvasId(canvasId)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Pixel> save(Pixel pixel) {
        return pixelRepository.save(pixel)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Pixel> getPixelById(Long id) {
        return pixelRepository.findById(id)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
