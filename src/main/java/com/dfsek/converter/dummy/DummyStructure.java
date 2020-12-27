package com.dfsek.converter.dummy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.UUID;

public class DummyStructure implements Serializable {
    private static final long serialVersionUID = -6664585217063842035L;
    private final DummyBlock[][][] structure;
    private final DummyInfo structureInfo;
    private final String id;
    private final UUID uuid;
    private final HashSet<DummyBlock> spawns;
    private final HashSet<DummyInventory> inventories;

    public DummyStructure(DummyBlock[][][] structure, DummyInfo structureInfo, String id, UUID uuid, HashSet<DummyBlock> spawns, HashSet<DummyInventory> inventories) {
        this.structure = structure;
        this.structureInfo = structureInfo;
        this.id = id;
        this.uuid = uuid;
        this.spawns = spawns;
        this.inventories = inventories;
    }

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
