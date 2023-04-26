package br.ufrn.dimap.collaborativecanvas.canvaservice.controller;

import br.ufrn.dimap.collaborativecanvas.canvaservice.model.Canvas;
import br.ufrn.dimap.collaborativecanvas.canvaservice.service.CanvaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/canva")
public class CanvasController {

    private final CanvaService canvaService;
    public CanvasController(@Autowired CanvaService canvaService) {
        this.canvaService = canvaService;
    }

    @GetMapping
    public Canvas getCanvaByLink(@RequestParam String link) {
        return canvaService.getCanvaByLink(link);
    }

}
