package com.dfsek.converter.formats.tstructure.dummy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.UUID;

public class DummyStructure implements Serializable {
    private static final long serialVersionUID = -6664585217063842035L;
    private DummyBlock[][][] structure;
    private DummyInfo structureInfo;
    private String id;
    private UUID uuid;
    private HashSet<DummyBlock> spawns;
    private HashSet<DummyInventory> inventories;

    public String getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public DummyInfo getStructureInfo() {
        return structureInfo;
    }

    public DummyBlock[][][] getStructure() {
        return structure;
    }

    public HashSet<DummyBlock> getSpawns() {
        return spawns;
    }

    public HashSet<DummyInventory> getInventories() {
        return inventories;
    }
}
