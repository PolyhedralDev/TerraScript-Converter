package com.dfsek.converter.formats.ewg.dummy;

import java.io.Serializable;

public class DummyStructure implements Serializable {
    private static final long serialVersionUID = -905274143366977303L;

    private DummyBlock[] blocks;

    private String name;

    public String getName() {
        return name;
    }

    public DummyBlock[] getBlocks() {
        return blocks;
    }
}
