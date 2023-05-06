package br.ufrn.dimap.collaborativecanvas.canvaservice.model;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "canvas")
public class Canvas {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Long creatorId;
    @Column(unique=true)
    private String link;
    private long qtdPaintedPixels;

    @OneToMany(mappedBy = "canvas", cascade = CascadeType.ALL)
    private List<Pixel> pixels;

    @OneToMany(mappedBy = "canvas", cascade = CascadeType.ALL)
    private List<History> histories;

    public Canvas() {
    }

    public Canvas(Long id, @NotNull String name, @NotNull Long creatorId, @NotNull String link, int ySize, int xSize) {
        this.id = id;
        this.name = name;
        this.creatorId = creatorId;
        this.link = link;
        this.qtdPaintedPixels = 0;
        this.pixels = new ArrayList<>();
        this.histories = new ArrayList<>();
        this.initializePixels(ySize, xSize);
    }
    private void initializePixels(int ySize, int xSize) {
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                this.pixels.add(new Pixel(j, i, "white", this));
            }
        }
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

    public @NotNull Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(@NotNull Long creatorId) {
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

    public Pixel getPixelById(Long id) {
        return this.pixels.stream()
                .filter(pixel -> pixel.getId().equals(id))
                .findFirst()
                .orElse(null);

    }

    public void addHistory(History history) {
        this.histories.add(history);
    }

    public List<History> getNthHistories(int n) {
        return this.histories.stream()
                .sorted(Comparator.comparingLong(History::getId).reversed())
                .limit(n)
                .toList();

    }

    public CanvasInfoDTO toCanvasInfoDTO() {
        return new CanvasInfoDTO(this.id, this.name, this.link, this.creatorId, this.qtdPaintedPixels);
    }

}
