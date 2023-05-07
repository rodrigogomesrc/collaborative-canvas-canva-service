package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.controller;

import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.Canvas;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.CanvasInfoDTO;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.CreateCanvasDTO;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.History;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.service.CanvasService;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.service.HistoryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@RestController
@RequestMapping("/canvas")
public class CanvasController {

    private final CanvasService canvasService;
    private final HistoryService historyService;

    public CanvasController(@Autowired CanvasService canvasService, @Autowired HistoryService historyService) {
        this.canvasService = canvasService;
        this.historyService = historyService;
    }

    /*
    @PostMapping
    public ResponseEntity<Canvas> createCanvas(@RequestBody @NotNull CreateCanvasDTO createCanvasDTO) {
        if (createCanvasDTO.name() == null || createCanvasDTO.creatorId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Canvas createdCanvas = canvasService.createCanvas(createCanvasDTO.name(), createCanvasDTO.creatorId());
        return ResponseEntity.status(201).body(createdCanvas);
    }

    @GetMapping("/last-histories")
    public Flux<History> getLastNHistories(@RequestParam @NotNull Long canvasId, @RequestParam @NotNull Integer n){
        return historyService.getTopNHistoriesFromCanvas(canvasId, n).subscribeOn(Schedulers.boundedElastic());
    }

    @GetMapping("/last-histories/update")
    public Flux<History> getLastNHistoriesWithUpdates(
            @RequestParam @NotNull Long canvasId, @RequestParam @NotNull Integer n){
        return historyService.getTopNHistoriesFromCanvasWithUpdates(canvasId, n);
    }

    @GetMapping("/top")
    public List<CanvasInfoDTO> getTop(@RequestParam @NotNull Integer n){
        return canvasService.getTopNCanvas(n);
    }

     */

}