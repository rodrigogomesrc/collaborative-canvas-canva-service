package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.controller;

import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.PaintingDTO;
import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.service.CanvasService;
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

@RestController
@RequestMapping("/painting")
public class PaintingController {

    //@Autowired
    //private RedissonReactiveClient cacheClient;

    private final CanvasService canvasService;

   // private final RMapCacheReactive<String, Canvas> canvasCache;

    public PaintingController(@Autowired CanvasService canvasService) {
        this.canvasService = canvasService;
        //this.canvasCache = cacheClient.getMapCache("canvas-cache");
    }

    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<ResponseEntity<Void>> processPainting(@RequestBody PaintingDTO painting) {
        if (painting == null || painting.canvasId() == null
                || painting.playerId() == null
                || painting.pixelId() == null
                || painting.color() == null) {
            return Mono.just(ResponseEntity.badRequest().build());
        }

        return canvasService.processPainting(painting)
                .map(processingStatus -> {
                    if (processingStatus) {
                        return ResponseEntity.ok().build();
                    }
                    return ResponseEntity.badRequest().build();
                });
    }
}
