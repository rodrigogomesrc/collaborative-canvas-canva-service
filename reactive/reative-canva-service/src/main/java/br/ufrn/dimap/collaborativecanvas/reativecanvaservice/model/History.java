package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("history")
public class History {

    @Id
    private Long id;
    private Long playerId;

    @JsonIgnore
    @Column("pixel_id")
    private Long pixelId;

    @JsonIgnore
    @Column("canvas_id")
    private Long canvasId;

    public History() {
    }

    public History(Long playerId, Long pixelId, Long canvasId) {
        this.playerId = playerId;
        this.pixelId = pixelId;
        this.canvasId = canvasId;
    }

    public Long getPixelId() {
        return pixelId;
    }

    public void setPixelId(Long pixelId) {
        this.pixelId = pixelId;
    }

    public Long getCanvasId() {
        return canvasId;
    }

    public void setCanvasId(Long canvasId) {
        this.canvasId = canvasId;
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

}
