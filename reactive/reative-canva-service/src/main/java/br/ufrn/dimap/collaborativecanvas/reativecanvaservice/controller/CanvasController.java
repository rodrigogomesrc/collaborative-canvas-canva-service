package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.controller;

import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.*;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.service.CanvasService;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.service.HistoryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/canvas")
public class CanvasController {

    private final CanvasService canvasService;
    private final HistoryService historyService;

    public CanvasController(@Autowired CanvasService canvasService, @Autowired HistoryService historyService) {
        this.canvasService = canvasService;
        this.historyService = historyService;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<CanvasDataDTO> getCanvasByLink(@RequestParam("link") @NotNull String link) {
        return canvasService.getCanvasByLink(link);
    }

    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<CanvasDataDTO> createCanvas(@RequestBody @NotNull CreateCanvasDTO createCanvasDTO) {
        if (createCanvasDTO.name() == null || createCanvasDTO.creatorId() == null) {
            return Mono.empty();
        }
        return canvasService.createCanvas(createCanvasDTO.name(), createCanvasDTO.creatorId());
    }


    @GetMapping(value="/last-histories", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<History> getLastNHistories(@RequestParam @NotNull Long canvasId, @RequestParam @NotNull Integer n){
        return historyService.getTopNHistoriesFromCanvas(canvasId, n).subscribeOn(Schedulers.boundedElastic());
    }

    @GetMapping(value="/last-histories/update", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<History> getLastNHistoriesWithUpdates(
            @RequestParam @NotNull Long canvasId, @RequestParam @NotNull Integer n){
        return historyService.getTopNHistoriesFromCanvasWithUpdates(canvasId, n);
    }

    @GetMapping(value="/top",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Canvas> getTop(@RequestParam @NotNull Integer n){
        return canvasService.getTopNCanvas(n);
    }


}