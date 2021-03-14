package com.dfsek.converter.formats.tstructure.dummy;

import java.io.Serializable;

public class DummyInfo implements Serializable {
    private static final long serialVersionUID = -175639605885943678L;
    private int sizeX;
    private int sizeY;
    private int sizeZ;
    private int centerX;
    private int centerZ;

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
