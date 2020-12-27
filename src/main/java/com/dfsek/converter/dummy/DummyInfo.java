package com.dfsek.converter.dummy;

import java.io.Serializable;

public class DummyInfo implements Serializable {
    private static final long serialVersionUID = -175639605885943678L;
    private final int sizeX;
    private final int sizeY;
    private final int sizeZ;
    private final int centerX;
    private final int centerZ;

    public DummyInfo(int sizeX, int sizeY, int sizeZ, int centerX, int centerZ) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
        this.centerX = centerX;
        this.centerZ = centerZ;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeZ() {
        return sizeZ;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterZ() {
        return centerZ;
    }
}
