package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model;

public class GetLastHistoriesDTO {

    private long canvasId;

    private int n;

    public GetLastHistoriesDTO() {
    }

    public GetLastHistoriesDTO(long canvasId, int n) {
        this.canvasId = canvasId;
        this.n = n;
    }

    public long getCanvasId() {
        return canvasId;
    }

    public void setCanvasId(long canvasId) {
        this.canvasId = canvasId;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}