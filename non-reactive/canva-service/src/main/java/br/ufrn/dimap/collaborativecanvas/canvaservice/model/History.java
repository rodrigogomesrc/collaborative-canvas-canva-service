package br.ufrn.dimap.collaborativecanvas.canvaservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue
    private Long id;
    private int playerId;
    private int paintingId;

    @ManyToOne
    @JoinColumn(name = "canvas_id")
    private Canvas canva;

    public History() {
    }

    public History(int playerId, int paintingId) {
        this.playerId = playerId;
        this.paintingId = paintingId;
    }

    public Canvas getCanva() {
        return canva;
    }

    public void setCanva(Canvas canva) {
        this.canva = canva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getPaintingId() {
        return paintingId;
    }

    public void setPaintingId(int paintingId) {
        this.paintingId = paintingId;
    }

}
