package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
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

    public Canvas(Long id, @NotNull String name, @NotNull Long creatorId, @NotNull String link) {
        this.id = id;
        this.name = name;
        this.creatorId = creatorId;
        this.link = link;
        this.qtdPaintedPixels = 0;
    }
    public List<Pixel> getPixels(int ySize, int xSize) {
        List<Pixel> createdPixels = new ArrayList<>();
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                createdPixels.add(new Pixel(j, i, "white", this.getId()));
            }
        }
        return createdPixels;
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

    public CanvasInfoDTO toCanvasInfoDTO() {
        return new CanvasInfoDTO(this.id, this.name, this.link, this.creatorId, this.qtdPaintedPixels);
    }

}

