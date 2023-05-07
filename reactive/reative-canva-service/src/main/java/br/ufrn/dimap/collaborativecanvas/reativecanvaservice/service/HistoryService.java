package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.service;


import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryService(@Autowired HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }
    /*

    public Flux<History> getTopNHistoriesFromCanvas(Long canvasId, int n) {
        return Mono.fromCallable(() -> historyRepository.findLastNHistories(canvasId, n).orElseGet(ArrayList::new))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMapMany(Flux::fromIterable);
    }

    public Flux<History> getTopNHistoriesFromCanvasWithUpdates(Long canvasId, int n) {
        return Flux.interval(Duration.ofSeconds(1))
                .publishOn(Schedulers.boundedElastic())
                .map(i -> historyRepository.findLastNHistories(canvasId, n)
                        .orElseGet(ArrayList::new))
                .flatMapIterable(histories -> histories);
    }


     */

}
