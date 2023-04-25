package br.ufrn.dimap.collaborativecanvas.canvaservice.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "canva")
public class Canva {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int creatorId;
    private String link;
    private long qtdPaintedPixels;


    @OneToMany(mappedBy = "canva")
    private List<Pixel> pixels;

    @OneToMany(mappedBy = "canva")
    private List<History> histories;

    public Canva() {
    }

    public Canva(Long id, String name, int creatorId, String link, long qtdPaintedPixels, List<Pixel> pixels, List<History> histories) {
        this.id = id;
        this.name = name;
        this.creatorId = creatorId;
        this.link = link;
        this.qtdPaintedPixels = qtdPaintedPixels;
        this.pixels = pixels;
        this.histories = histories;
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

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
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

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

}
