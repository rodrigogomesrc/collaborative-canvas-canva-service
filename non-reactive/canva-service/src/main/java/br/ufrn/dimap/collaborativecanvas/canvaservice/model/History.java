package br.ufrn.dimap.collaborativecanvas.canvaservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue
    private Long id;
    private Long playerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pixel_id", referencedColumnName = "id")
    private Pixel pixel;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "canvas_id")
    private Canvas canvas;

    public History() {
    }

    public History(Long playerId, Pixel pixel) {
        this.playerId = playerId;
        this.pixel = pixel;
    }

    public Pixel getPixel() {
        return pixel;
    }

    public void setPixel(Pixel pixel) {
        this.pixel = pixel;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canva) {
        this.canvas = canva;
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
