package com.dfsek.converter.formats.ewg.dummy;

import java.io.Serializable;

public class DummyBlock implements Serializable {
    private static final long serialVersionUID = 2610063934261982315L;

    private int x;

    private int y;

    private int z;

    private int id;

    private String meta;

    private String blockData;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public String getBlockData() {
        return blockData;
    }
}
