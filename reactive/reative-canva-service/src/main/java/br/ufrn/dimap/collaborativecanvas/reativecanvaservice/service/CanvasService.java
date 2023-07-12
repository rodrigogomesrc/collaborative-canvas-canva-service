package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.service;

import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.*;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.repository.CanvasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CanvasService {

    private final CanvasRepository canvasRepository;
    private final PixelService pixelService;
    private final HistoryService historyService;
    private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

    public CanvasService(
            @Autowired CanvasRepository canvasRepository,
            @Autowired PixelService pixelService,
            @Autowired HistoryService historyService){
        this.canvasRepository = canvasRepository;
        this.pixelService = pixelService;
        this.historyService = historyService;
    }

    public Mono<CanvasDataDTO> getCanvasByLink(String link) {
        return canvasRepository.findByLink(link)
            .subscribeOn(Schedulers.boundedElastic())
            .flatMap(canvas -> {
                Mono<List<Pixel>> pixels = pixelService.getPixelsFromCanvas(canvas.getId()).collectList();
                    return pixels.map(savedPixels -> new CanvasDataDTO(canvas, savedPixels));
            });
    }

    public Mono<CanvasDataDTO> getCanvasById(Long id) {
        return canvasRepository.findById(id)
            .subscribeOn(Schedulers.boundedElastic())
            .flatMap(canvas -> {
                Mono<List<Pixel>> pixels = pixelService.getPixelsFromCanvas(canvas.getId()).collectList();
                    return pixels.map(savedPixels -> new CanvasDataDTO(canvas, savedPixels));
            });
    }

    private String createRandomLink() {
        return IntStream.range(0, 15)
                .mapToObj(i -> (char) (Math.random() * 26 + 97))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }


    public Mono<CanvasDataDTO> createCanvas(String name, long creatorId) {
        Canvas newCanvas = new Canvas(null, name, creatorId, createRandomLink());

        return canvasRepository.save(newCanvas)
                .flatMap(savedCanvas -> {
                    List<Pixel> pixels = savedCanvas.getPixels(50, 50);

                    return Flux.fromIterable(pixels)
                            .parallel()
                            .runOn(Schedulers.fromExecutor(executorService))
                            //.runOn(Schedulers.boundedElastic())
                            .flatMap(pixelService::save)
                            .sequential()
                            .collectList()
                            .map(savedPixels -> new CanvasDataDTO(savedCanvas, savedPixels));
                });
    }

    public Mono<Boolean> processPainting(PaintingDTO painting) {
        Long canvasId = painting.canvasId();
        Mono<Canvas> canvasMono = canvasRepository.findById(canvasId);
        return canvasMono.flatMap(canvas -> {
            canvas.setQtdPaintedPixels(canvas.getQtdPaintedPixels() + 1);
            return pixelService.getPixelById(painting.pixelId()).flatMap(pixel -> {
                pixel.setColor(painting.color());
                History history = new History(painting.playerId(), pixel.getId(), canvas.getId());
                return historyService.save(history).flatMap(savedHistory -> canvasRepository.save(canvas)
                        .map(savedCanvas -> true));
            }).defaultIfEmpty(false).subscribeOn(Schedulers.fromExecutor(executorService));
        }).defaultIfEmpty(false).subscribeOn(Schedulers.fromExecutor(executorService));
    }


    public Flux<Canvas> getTopNCanvas(int n){
        return canvasRepository.findTopNByPaintedPixels(n)
                .subscribeOn(Schedulers.boundedElastic()
        );

    }


}