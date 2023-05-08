package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model;

public class HistoryDataDTO {

    private Long id;

    private Long playerId;

    private Long canvasId;

    private Pixel pixel;


    public HistoryDataDTO() {
    }

    public HistoryDataDTO(History history, Pixel pixel) {
        this.id = history.getId();
        this.playerId = history.getPlayerId();
        this.canvasId = history.getCanvasId();
        this.pixel = pixel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getCanvasId() {
        return canvasId;
    }

    public void setCanvasId(Long canvasId) {
        this.canvasId = canvasId;
    }

    public Pixel getPixel() {
        return pixel;
    }

    public void setPixel(Pixel pixel) {
        this.pixel = pixel;
    }
}
