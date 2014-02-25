package biz.pavonis.golservice.internal;

public enum CellNeighbor {

    TOP_LEFT(-1, 1), TOP(0, 1), TOP_RIGHT(1, 1), RIGHT(1, 0), BOTTOM_RIGHT(1, -1), BOTTOM(0, -1), BOTTOM_LEFT(-1, -1), LEFT(-1, 0);

    private int xOffset;
    private int yOffset;

    private CellNeighbor(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }
}
