package br.ufrn.dimap.collaborativecanvas.canvaservice.controller;

import br.ufrn.dimap.collaborativecanvas.canvaservice.model.Canvas;
import br.ufrn.dimap.collaborativecanvas.canvaservice.model.CanvasInfoDTO;
import br.ufrn.dimap.collaborativecanvas.canvaservice.model.CreateCanvasDTO;
import br.ufrn.dimap.collaborativecanvas.canvaservice.model.History;
import br.ufrn.dimap.collaborativecanvas.canvaservice.service.CanvasService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/{id}")
    public Canvas getCanvaById(@PathVariable @NotNull Long id) {
        return canvasService.getCanvaById(id);
    }

    @PostMapping
    public ResponseEntity<Canvas> createCanvas(@RequestBody @NotNull CreateCanvasDTO createCanvasDTO) {
        if (createCanvasDTO.name() == null || createCanvasDTO.creatorId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Canvas createdCanvas = canvasService.createCanvas(createCanvasDTO.name(), createCanvasDTO.creatorId());
        return ResponseEntity.status(201).body(createdCanvas);
    }

    @GetMapping("/last-histories")
    public List<History> getLastNHistories(@RequestParam @NotNull Long canvasId, @RequestParam @NotNull Integer n){
        return canvasService.getLastNHistories(canvasId, n);
    }

    @GetMapping("/top")
    public List<CanvasInfoDTO> getTop(@RequestParam @NotNull Integer n){
        return canvasService.getTopNCanvas(n);
    }


}
