package com.dfsek.converter.formats.ewg;

import com.dfsek.converter.Converter;
import com.dfsek.converter.ScriptBuilder;
import com.dfsek.converter.formats.ewg.dummy.DummyBlock;
import com.dfsek.converter.formats.ewg.dummy.DummyStructure;

import com.dfsek.converter.util.MovedObjectInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class EWGConverter implements Converter {
    @Override
    public String convert(InputStream in) {
        Map<String, String> converter = new HashMap<>();

        converter.put("com.minelazz.epicworldgenerator.structures.StructureObject", "com.dfsek.converter.formats.ewg.dummy.DummyStructure");
        converter.put("com.minelazz.epicworldgenerator.structures.StructureObject$SOBlock", "com.dfsek.converter.formats.ewg.dummy.DummyBlock");

        try(MovedObjectInputStream inputStream = new MovedObjectInputStream(in, converter)) {
            Object o = inputStream.readObject();
            DummyStructure structure = (DummyStructure) o;

            System.out.println("\nLoaded EWG structure: " + structure.getName());
            System.out.println("Converting structure to TerraScript...");

            ScriptBuilder scriptBuilder = new ScriptBuilder();

            for(DummyBlock block : structure.getBlocks()) {
                scriptBuilder.block(block.getX(), block.getY(), block.getZ(), block.getBlockData());
            }

            return scriptBuilder.build();
        } catch(ClassNotFoundException | IOException e) {
            System.err.println("Failed to convert EWG structure!");
            e.printStackTrace();
            System.err.println("Please report this issue.");
        }
        return null;
    }
}
