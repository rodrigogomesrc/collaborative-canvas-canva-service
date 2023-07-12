package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model;
import java.util.List;

public class CanvasDataDTO {

    private Long id;
    private String name;
    private Long creatorId;
    private String link;
    private long qtdPaintedPixels;

    private List<Pixel> pixels;

    public CanvasDataDTO(Canvas canvas, List<Pixel> pixels){
        this.id = canvas.getId();
        this.name = canvas.getName();
        this.creatorId = canvas.getCreatorId();
        this.link = canvas.getLink();
        this.qtdPaintedPixels = canvas.getQtdPaintedPixels();
        this.pixels = pixels;
    }

    public CanvasDataDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getQtdPaintedPixels() {
        return qtdPaintedPixels;
    }

    public void setQtdPaintedPixels(long qtdPaintedPixels) {
        this.qtdPaintedPixels = qtdPaintedPixels;
    }

    public List<Pixel> getPixels() {
        return pixels;
    }

    public void setPixels(List<Pixel> pixels) {
        this.pixels = pixels;
    }
}