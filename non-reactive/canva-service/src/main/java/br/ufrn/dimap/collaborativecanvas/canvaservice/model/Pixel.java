package br.ufrn.dimap.collaborativecanvas.canvaservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pixel")
public class Pixel {

    @Id
    @GeneratedValue
    private Long id;
    private Integer x;
    private Integer y;
    private String color;
    @ManyToOne
    @JoinColumn(name = "canva_id")
    private Canvas canva;

    public Pixel() {
    }

    public Pixel(Integer x, Integer y, String color, Canvas canva) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.canva = canva;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Canvas getCanva() {
        return canva;
    }

    public void setCanva(Canvas canva) {
        this.canva = canva;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
