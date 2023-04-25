package br.ufrn.dimap.collaborativecanvas.canvaservice.model;

import jakarta.persistence.*;

@Entity
@Table
public class Pixel {

    private Integer x;
    private Integer y;
    private String color;
    @ManyToOne
    private Canva canva;
    @Id
    @GeneratedValue
    private Long id;

    public Pixel() {
    }

    public Pixel(Integer x, Integer y, String color, Canva canva) {
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

    public Canva getCanva() {
        return canva;
    }

    public void setCanva(Canva canva) {
        this.canva = canva;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
