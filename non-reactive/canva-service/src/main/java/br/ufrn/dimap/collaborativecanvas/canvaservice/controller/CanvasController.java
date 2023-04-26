package br.ufrn.dimap.collaborativecanvas.canvaservice.controller;

import br.ufrn.dimap.collaborativecanvas.canvaservice.model.Canvas;
import br.ufrn.dimap.collaborativecanvas.canvaservice.service.CanvasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/canva")
public class CanvasController {

    private final CanvasService canvasService;
    public CanvasController(@Autowired CanvasService canvasService) {
        this.canvasService = canvasService;
    }

    @GetMapping
    public Canvas getCanvaByLink(@RequestParam String link) {
        return canvasService.getCanvaByLink(link);
    }

}
