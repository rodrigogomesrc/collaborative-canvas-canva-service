package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("pixel")
public class Pixel {

    @Id
    private Long id;

    @Column("x")
    private Integer x;

    @Column("y")
    private Integer y;

    @Column("color")
    private String color;

    @JsonIgnore
    @Column("canvas_id")
    private Long canvasId;

    public Pixel() {
    }

    public Pixel(Integer x, Integer y, String color, Long canvasId) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.canvasId = canvasId;
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

    public Long getCanvasId() {
        return canvasId;
    }

    public void setCanvasId(Long canvasId) {
        this.canvasId = canvasId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
