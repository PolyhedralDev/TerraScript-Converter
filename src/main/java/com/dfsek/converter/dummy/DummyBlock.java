package com.dfsek.converter.dummy;

import java.io.Serializable;

public class DummyBlock implements Serializable {
    private static final long serialVersionUID = 6143969483382710947L;
    private final DummyBlockData bl;
    private final Pull pull;
    private final int pullOffset;
    private final int x;
    private final int y;
    private final int z;
    //private final SerializableBlockState state;
    private final DummySpawn requirement;

    public DummyBlock(DummyBlockData bl, Pull pull, int pullOffset, int x, int y, int z, DummySpawn requirement) {
        this.bl = bl;
        this.pull = pull;
        this.pullOffset = pullOffset;
        this.x = x;
        this.y = y;
        this.z = z;
        this.requirement = requirement;
    }

    public enum Pull {
        UP, NONE, DOWN
    }

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
}
