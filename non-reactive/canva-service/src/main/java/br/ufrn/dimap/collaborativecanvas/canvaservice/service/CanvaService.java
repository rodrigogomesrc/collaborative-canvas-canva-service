package br.ufrn.dimap.collaborativecanvas.canvaservice.service;

import br.ufrn.dimap.collaborativecanvas.canvaservice.Repository.CanvasRepository;
import br.ufrn.dimap.collaborativecanvas.canvaservice.model.Canvas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CanvaService {

    private final CanvasRepository canvasRepository;
    public CanvaService(@Autowired CanvasRepository canvasRepository) {
        this.canvasRepository = canvasRepository;
    }

    public Canvas getCanvaByLink(String link) {
        Optional<Canvas> canva = canvasRepository.findByLink(link);
        return canva.orElse(null);
    }


}
