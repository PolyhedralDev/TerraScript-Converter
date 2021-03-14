package com.dfsek.converter.formats.tstructure;

import com.dfsek.converter.Converter;
import com.dfsek.converter.ScriptBuilder;
import com.dfsek.converter.formats.tstructure.dummy.DummyBlock;
import com.dfsek.converter.formats.tstructure.dummy.DummyStructure;
import com.dfsek.converter.util.MovedObjectInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class TStructureConverter implements Converter {
    @Override
    public String convert(InputStream in) {
        Map<String, String> converter = new HashMap<>();
        converter.put("com.dfsek.terra.structure.Structure", "com.dfsek.converter.formats.tstructure.dummy.DummyStructure");
        converter.put("com.dfsek.terra.structure.StructureSpawnRequirement", "com.dfsek.converter.formats.tstructure.dummy.DummySpawn");
        converter.put("com.dfsek.terra.structure.StructureContainedInventory", "com.dfsek.converter.formats.tstructure.dummy.DummyInventory");
        converter.put("com.dfsek.terra.structure.StructureInfo", "com.dfsek.converter.formats.tstructure.dummy.DummyInfo");
        converter.put("com.dfsek.terra.structure.serialize.SerializableBlockData", "com.dfsek.converter.formats.tstructure.dummy.DummyBlockData");
        converter.put("com.dfsek.terra.structure.StructureContainedBlock", "com.dfsek.converter.formats.tstructure.dummy.DummyBlock");
        converter.put("com.dfsek.terra.structure.StructureContainedBlock$Pull", "com.dfsek.converter.formats.tstructure.dummy.DummyBlock$Pull");

        try(MovedObjectInputStream inputStream = new MovedObjectInputStream(in, converter)) {
            Object o = inputStream.readObject();
            DummyStructure structure = (DummyStructure) o;

            ScriptBuilder scriptBuilder = new ScriptBuilder();

            for(DummyBlock spawn : structure.getSpawns()) {
                scriptBuilder.check(spawn.getX(), spawn.getY(), spawn.getZ(), spawn.getRequirement().toString());
            }

            for(DummyBlock[][] blocks : structure.getStructure()) {
                for(DummyBlock[] blocks1 : blocks) {
                    for(DummyBlock block : blocks1) {
                        if(!block.getBl().getData().equals("minecraft:structure_void")) {
                            String data = block.getBl().getData();
                            scriptBuilder.block(block.getX(), block.getY(), block.getZ(), data);
                        }
                    }
                }
            }

            return scriptBuilder.build();
        } catch(ClassNotFoundException | IOException e) {
            System.err.println("Failed to convert structure!");
            e.printStackTrace();
            System.err.println("Please report this issue.");
        }
        return null;
    }
}
