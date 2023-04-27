package br.ufrn.dimap.collaborativecanvas.canvaservice.controller;

import br.ufrn.dimap.collaborativecanvas.canvaservice.model.Canvas;
import br.ufrn.dimap.collaborativecanvas.canvaservice.service.CanvasService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/canvas")
public class CanvasController {

    private final CanvasService canvasService;
    public CanvasController(@Autowired CanvasService canvasService) {
        this.canvasService = canvasService;
    }

    @GetMapping
    public Canvas getCanvaByLink(@RequestParam @NotNull String link) {
        return canvasService.getCanvaByLink(link);
    }

    @PostMapping
    public void createCanvas(@RequestBody @NotNull String name, @RequestBody @NotNull Long creatorId) {
        canvasService.createCanvas(name, creatorId);
    }

    @GetMapping("/last-histories")
    public Object getLastNHistories(@RequestParam @NotNull Long canvasId, @RequestParam @NotNull Integer n){
        return canvasService.getLastNHistories(canvasId, n);
    }

    @GetMapping("/top")
    public Object getTop(@RequestParam @NotNull Integer n){
        return canvasService.getTopNCanvas(n);
    }


}
