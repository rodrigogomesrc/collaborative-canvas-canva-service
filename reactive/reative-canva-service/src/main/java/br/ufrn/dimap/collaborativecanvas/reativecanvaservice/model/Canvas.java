package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Table("canvas")
public class Canvas {

    @Id
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @Column("creator_id")
    private Long creatorId;

    @Column("link")
    private String link;
    @Column("qtd_painted_pixels")
    private long qtdPaintedPixels;

    @JsonIgnore
    private List<Pixel> pixels = new ArrayList<>();

    @JsonIgnore
    private List<History> histories = new ArrayList<>();


    public Canvas(Long id, @NotNull String name, @NotNull Long creatorId, @NotNull String link, int ySize, int xSize) {
        this.id = id;
        this.name = name;
        this.creatorId = creatorId;
        this.link = link;
        this.qtdPaintedPixels = 0;
        this.initializePixels(ySize, xSize);
    }
    private void initializePixels(int ySize, int xSize) {
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                this.pixels.add(new Pixel(j, i, "white", this.getId()));
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

