package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.service;

import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.*;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.repository.CanvasRepository;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.repository.PixelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class CanvasService {

    private final CanvasRepository canvasRepository;
    private final PixelService pixelService;
    private final PixelRepository pixelRepository;

    public CanvasService(
            @Autowired CanvasRepository canvasRepository,
            @Autowired PixelService pixelService,
            @Autowired PixelRepository pixelRepository){
        this.canvasRepository = canvasRepository;
        this.pixelService = pixelService;
        this.pixelRepository = pixelRepository;
    }

    public Mono<CanvasDataDTO> getCanvasByLink(String link) {
        return canvasRepository.findByLink(link)
                .flatMap(canvas -> {
                    Mono<List<Pixel>> pixels = pixelService.getPixelsFromCanvas(canvas.getId()).collectList();
                    return pixels.map(savedPixels -> new CanvasDataDTO(canvas, savedPixels));
                });
    }

    private String createRandomLink() {
        StringBuilder randomLink = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            randomLink.append((char) (Math.random() * 26 + 97));
        }
        return randomLink.toString();
    }

    public Mono<CanvasDataDTO> createCanvas(String name, long creatorId) {
        Canvas newCanvas = new Canvas(null, name, creatorId, createRandomLink());

        return canvasRepository.save(newCanvas)
                .flatMap(savedCanvas -> {
                    List<Pixel> pixels = savedCanvas.getPixels(50, 50);

                    return Flux.fromIterable(pixels)
                            .parallel()
                            .runOn(Schedulers.boundedElastic())
                            .flatMap(pixelRepository::save)
                            .sequential()
                            .collectList()
                            .map(savedPixels -> new CanvasDataDTO(savedCanvas, savedPixels));
                });
    }
    /*

    public boolean processPainting(PaintingDTO painting){
        Long canvasId = painting.canvasId();
        Optional<Canvas> canva = canvasRepository.findById(canvasId);
        if(canva.isPresent()){
            Canvas canvas = canva.get();
            canvas.setQtdPaintedPixels(canvas.getQtdPaintedPixels() + 1);
            Pixel pixel = canvas.getPixelById(painting.pixelId());
            if (pixel == null) {
                return false;
            }
            pixel.setColor(painting.color());
            History history = new History(painting.playerId(), pixel, canvas);
            canvas.addHistory(history);
            canvasRepository.save(canvas);
            return true;
        }
        return false;
    }
    /*
     */

    /*
    public List<CanvasInfoDTO> getTopNCanvas(int n){
        Optional<List<Canvas>> canvas = canvasRepository.findTopNByPaintedPixels(n);
        if(canvas.isEmpty()){
            return new ArrayList<>();
        }
        List<Canvas> canvasList =  canvas.get();
        return canvasList.stream().map(Canvas::toCanvasInfoDTO).collect(Collectors.toList());
    }

     */


}