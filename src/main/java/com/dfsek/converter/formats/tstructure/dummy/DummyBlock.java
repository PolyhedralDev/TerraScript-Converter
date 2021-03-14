package com.dfsek.converter.formats.tstructure.dummy;

import java.io.Serializable;

public class DummyBlock implements Serializable {
    private static final long serialVersionUID = 6143969483382710947L;
    private DummyBlockData bl;
    private Pull pull;
    private int pullOffset;
    private int x;
    private int y;
    private int z;
    private DummySpawn requirement;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public DummyBlockData getBl() {
        return bl;
    }

    public Pull getPull() {
        return pull;
    }

    public int getPullOffset() {
        return pullOffset;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public DummySpawn getRequirement() {
        return requirement;
    }

    public enum Pull {
        UP, NONE, DOWN
    }
}
