package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.service;


import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.History;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(@Autowired HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public Flux<History> getTopNHistoriesFromCanvas(Long canvasId, int n) {
        return historyRepository.findLastNHistories(canvasId, n)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<History> getTopNHistoriesFromCanvasWithUpdates(Long canvasId, int n) {
        return Flux.interval(Duration.ofSeconds(1))
                .flatMap(tick -> historyRepository.findLastNHistories(canvasId, n)
                        .subscribeOn(Schedulers.boundedElastic()));
    }


}
