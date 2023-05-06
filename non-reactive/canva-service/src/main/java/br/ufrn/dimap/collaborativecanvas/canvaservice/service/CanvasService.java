package br.ufrn.dimap.collaborativecanvas.canvaservice.service;

import br.ufrn.dimap.collaborativecanvas.canvaservice.model.*;
import br.ufrn.dimap.collaborativecanvas.canvaservice.repository.CanvasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void processPaiting(PaintingDTO painting){
        Long canvaId = painting.canvasId();
        Optional<Canvas> canva = canvasRepository.findById(canvaId);
        if(canva.isPresent()){
            Canvas canvas = canva.get();
            canvas.setQtdPaintedPixels(canvas.getQtdPaintedPixels() + 1);
            Pixel pixel = canvas.getPixelById(painting.pixelId());
            canvas.addHistory(new History(painting.playerId(), pixel));
            canvasRepository.save(canvas);
        }
    }

    public List<History> getLastNHistories(Long canvasId, int n){
        Optional<Canvas> canva = canvasRepository.findById(canvasId);
        if(canva.isPresent()){
            Canvas canvas = canva.get();
            return canvas.getNthHistories(n);
        }
        return new ArrayList<>();
    }

    public List<CanvasInfoDTO> getTopNCanvas(int n){
        Optional<List<Canvas>> canvas = canvasRepository.findTopNByPaintedPixels(n);
        if(canvas.isEmpty()){
            return new ArrayList<>();
        }
        List<Canvas> canvasList =  canvas.get();
        return canvasList.stream().map(Canvas::toCanvasInfoDTO).collect(Collectors.toList());
    }

}
