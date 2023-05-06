package br.ufrn.dimap.collaborativecanvas.canvaservice.model;

public record CanvasInfoDTO(Long id, String name, String link, Long creatorId, Long qtdPaintedPixels){
}
