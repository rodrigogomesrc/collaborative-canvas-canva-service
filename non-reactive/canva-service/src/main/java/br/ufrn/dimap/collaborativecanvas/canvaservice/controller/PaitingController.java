package br.ufrn.dimap.collaborativecanvas.canvaservice.controller;

import br.ufrn.dimap.collaborativecanvas.canvaservice.exception.BadRequestException;
import br.ufrn.dimap.collaborativecanvas.canvaservice.model.PaintingDTO;
import br.ufrn.dimap.collaborativecanvas.canvaservice.service.CanvasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/painting")
public class PaitingController {

    private final CanvasService canvasService;
    public PaitingController(@Autowired CanvasService canvasService) {
       this.canvasService = canvasService;
    }

    @CachePut(value = "canvas-histories", key = "#painting.canvasId()")
    @CacheEvict(value = "canvas-by-id", key = "#painting.canvasId()")
    @PostMapping
    public String processPainting(@RequestBody PaintingDTO painting) {
        if( painting == null || painting.canvasId() == null
                || painting.playerId() == null
                || painting.pixelId() == null
                || painting.color() == null) {
            throw new BadRequestException();

        }
        boolean processingStatus = canvasService.processPainting(painting);
        if (processingStatus) {
            return "OK";
        }
        throw new BadRequestException();
    }
}

