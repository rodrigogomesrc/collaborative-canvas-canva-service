package br.ufrn.dimap.collaborativecanvas.canvaservice.model;

import java.io.Serializable;

public record CanvasInfoDTO(Long id, String name, String link, Long creatorId, Long qtdPaintedPixels) implements Serializable {
}
