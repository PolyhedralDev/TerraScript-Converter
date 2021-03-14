package com.dfsek.converter.formats.tstructure.dummy;

import java.io.Serializable;

public class DummyInventory implements Serializable {
    private static final long serialVersionUID = -175339605585943678L;
    private int uid;
    private int x, y, z;

    public int getZ() {
        return z;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getUid() {
        return uid;
    }
}
