package com.dfsek.converter.dummy;

import java.io.Serializable;

public class DummyInventory implements Serializable {
    private static final long serialVersionUID = -175339605585943678L;
    private final int uid;
    private final int x, y, z;

    public DummyInventory(int uid, int x, int y, int z) {
        this.uid = uid;
        this.x = x;
        this.y = y;
        this.z = z;
    }

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
