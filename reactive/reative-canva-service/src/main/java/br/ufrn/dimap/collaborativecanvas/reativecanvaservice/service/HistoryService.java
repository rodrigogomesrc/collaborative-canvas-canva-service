package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.service;

import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.History;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;
    public HistoryService(@Autowired HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<History> getTopNHistoriesFromCanvas(Long canvasId, int n){
        Optional<List<History>> histories = historyRepository.findLastNHistories(canvasId, n);
        return histories.orElseGet(ArrayList::new);
    }


}
