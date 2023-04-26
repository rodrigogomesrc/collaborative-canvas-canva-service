package br.ufrn.dimap.collaborativecanvas.canvaservice.service;

import br.ufrn.dimap.collaborativecanvas.canvaservice.repository.CanvasRepository;
import br.ufrn.dimap.collaborativecanvas.canvaservice.model.Canvas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CanvasService {

    private final CanvasRepository canvasRepository;
    public CanvasService(@Autowired CanvasRepository canvasRepository) {
        this.canvasRepository = canvasRepository;
    }

    public Canvas getCanvaByLink(String link) {
        Optional<Canvas> canva = canvasRepository.findByLink(link);
        return canva.orElse(null);
    }

    private String createRandomLink() {
        StringBuilder randomLink = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            randomLink.append((char) (Math.random() * 26 + 97));
        }
        return randomLink.toString();
    }

    public Canvas createCanvas(String name, long creatorId) {
        Canvas newCanvas = new Canvas(null, name, creatorId, createRandomLink(), 50, 50);
        canvasRepository.save(newCanvas);
        return newCanvas;
    }

}
