package br.ufrn.dimap.collaborativecanvas.canvaservice.model;

public record PaintingDTO(Long pixelId, Long playerId, Long canvasId, String color) {
}
