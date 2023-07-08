package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.controller;

import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.Canvas;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.CanvasDataDTO;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.PaintingDTO;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.service.CanvasService;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import org.redisson.api.RMapCacheReactive;
import org.redisson.api.RedissonReactiveClient;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/painting")
public class PaintingController {


    private final CanvasService canvasService;
   private final RMapCacheReactive<String, Canvas> canvasCache;

    public PaintingController(@Autowired CanvasService canvasService, @Autowired RedissonReactiveClient client) {
        this.canvasService = canvasService;
        this.canvasCache = client.getMapCache("/canvas/", new TypedJsonJacksonCodec(String.class, CanvasDataDTO.class));
    }

    /*
    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<ResponseEntity<Object>> processPainting(@RequestBody PaintingDTO painting) {
        return Mono.justOrEmpty(painting)
                .filter(this::isPaintingValid)
                .flatMap(canvasService::processPainting)
                .publishOn(Schedulers.boundedElastic())
                .map(processingStatus -> {
                    if (processingStatus) {
                        canvasCache.fastRemove("canvas-by-id::" + painting.canvasId())
                                .subscribe();
                        return ResponseEntity.ok().build();
                    }
                    return ResponseEntity.badRequest().build();
                })
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest().build()));
    }

     */

    private boolean isPaintingValid(PaintingDTO painting) {
        return painting.canvasId() != null && painting.playerId() != null &&
                painting.pixelId() != null && painting.color() != null;
    }

}
