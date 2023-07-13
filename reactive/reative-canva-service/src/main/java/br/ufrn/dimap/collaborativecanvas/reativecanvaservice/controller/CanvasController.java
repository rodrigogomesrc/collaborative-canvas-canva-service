package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.controller;

import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.*;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.service.CanvasService;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.service.HistoryService;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.redisson.api.RMapCacheReactive;
import org.redisson.api.RedissonReactiveClient;

import java.util.function.Consumer;

@RestController
@RequestMapping("/canvas")
public class CanvasController {

    private final CanvasService canvasService;
    private final HistoryService historyService;
    private final RMapCacheReactive<String, CanvasDataDTO> canvasCache;

    public CanvasController(@Autowired CanvasService canvasService,
                            @Autowired HistoryService historyService,
                            @Autowired RedissonReactiveClient client) {
        this.canvasService = canvasService;
        this.historyService = historyService;
        this.canvasCache = client.getMapCache("/canvas/", new TypedJsonJacksonCodec(String.class, CanvasDataDTO.class));
    }

    /*
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<CanvasDataDTO> getCanvasByLink(@RequestParam("link") @NotNull String link) {
        return canvasService.getCanvasByLink(link);
    }

     */

    /*
     @Bean
    public Consumer<String> getAllPlayers() {
        return v -> playerService.getAllPlayers().subscribe();
    }

     */

    @Bean
    public Consumer<String> getCanvasByLink() {
        return link -> canvasService.getCanvasByLink(link).subscribe();
    }

    /*
    @GetMapping(value = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<CanvasDataDTO> getCanvasById(@PathVariable @NotNull Long id) {
        return canvasCache.get("canvas-by-id::" + id).switchIfEmpty(
                this.canvasService.getCanvasById(id)
                        .flatMap(canvas -> this.canvasCache.fastPut("canvas-by-id::" + id, canvas).thenReturn(canvas))
        );
    }

     */

    @Bean
    public Consumer<Long> getCanvasById() {
        return id -> canvasService.getCanvasById(id).subscribe();
    }

    /*
    @GetMapping(value="/last-histories", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<HistoryDataDTO> getLastNHistories(@RequestParam @NotNull Long canvasId, @RequestParam @NotNull Integer n){
        return historyService.getTopNHistoriesFromCanvas(canvasId, n).subscribeOn(Schedulers.boundedElastic());
    }

     */

    @Bean
    public Consumer<GetLastHistoriesDTO> getLastNHistories() {
        return last -> historyService.getTopNHistoriesFromCanvas(last.getCanvasId(), last.getN()).subscribe();
    }

    /*
    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<CanvasDataDTO> createCanvas(@RequestBody @NotNull CreateCanvasDTO createCanvasDTO) {
        if (createCanvasDTO.name() == null || createCanvasDTO.creatorId() == null) {
            return Mono.empty();
        }
        return canvasService.createCanvas(createCanvasDTO.name(), createCanvasDTO.creatorId());
    }

     */

    @Bean
    public Consumer<CreateCanvasDTO> createCanvas() {
        return createCanvasDTO -> canvasService.createCanvas(createCanvasDTO.name(), createCanvasDTO.creatorId()).subscribe();
    }

    /*
    @GetMapping(value="/last-histories/update", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<HistoryDataDTO> getLastNHistoriesWithUpdates(
            @RequestParam @NotNull Long canvasId, @RequestParam @NotNull Integer n){
        return historyService.getTopNHistoriesFromCanvasWithUpdates(canvasId, n);
    }

     */

    /*
    @GetMapping(value="/top",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Canvas> getTop(@RequestParam @NotNull Integer n){
        return canvasService.getTopNCanvas(n);
    }

     */
    @Bean
    public Consumer<Integer> getTop() {
        return n -> canvasService.getTopNCanvas(n).subscribe();
    }



}