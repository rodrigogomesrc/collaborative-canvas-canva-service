package br.ufrn.dimap.collaborativecanvas.canvaservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "canvas_id")
    private Canvas canvas;

    public Pixel() {
    }

    public Pixel(Integer x, Integer y, String color, Canvas canvas) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.canvas = canvas;
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

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canva) {
        this.canvas = canva;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
