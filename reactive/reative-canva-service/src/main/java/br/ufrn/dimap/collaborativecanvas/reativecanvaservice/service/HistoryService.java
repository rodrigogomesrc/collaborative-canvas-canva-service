package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.service;

import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.History;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.HistoryDataDTO;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    private final PixelService pixelService;

    public HistoryService(@Autowired HistoryRepository historyRepository, @Autowired PixelService pixelService) {
        this.historyRepository = historyRepository;
        this.pixelService = pixelService;
    }

    public Flux<HistoryDataDTO> getTopNHistoriesFromCanvas(Long canvasId, int n) {
        return historyRepository.findLastNHistories(canvasId, n)
            .flatMap(history -> pixelService.getPixelById(history.getPixelId())
            .map(pixel -> new HistoryDataDTO(history, pixel)));
    }

    public Flux<HistoryDataDTO> getTopNHistoriesFromCanvasWithUpdates(Long canvasId, int n) {
        return Flux.interval(Duration.ofSeconds(1))
            .flatMap(tick -> historyRepository.findLastNHistories(canvasId, n)
            .flatMap(history -> pixelService.getPixelById(history.getPixelId())
            .subscribeOn(Schedulers.boundedElastic())
            .map(pixel -> new HistoryDataDTO(history, pixel))));
    }

    public Mono<HistoryDataDTO> save(History history) {
        return historyRepository.save(history)
            .flatMap(savedHistory -> pixelService.getPixelById(savedHistory.getPixelId())
            .subscribeOn(Schedulers.boundedElastic())
            .map(pixel -> new HistoryDataDTO(savedHistory, pixel)));
    }

}
