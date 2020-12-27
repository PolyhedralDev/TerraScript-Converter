package com.dfsek.converter;

import com.dfsek.converter.dummy.DummyBlock;
import com.dfsek.converter.dummy.DummyStructure;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ScriptConverter {
    public static void main(String[] args) throws IOException {
        Map<String, String> converter = new HashMap<>();
        converter.put("com.dfsek.terra.structure.Structure", "com.dfsek.converter.dummy.DummyStructure");
        converter.put("com.dfsek.terra.structure.StructureSpawnRequirement", "com.dfsek.converter.dummy.DummySpawn");
        converter.put("com.dfsek.terra.structure.StructureContainedInventory", "com.dfsek.converter.dummy.DummyInventory");
        converter.put("com.dfsek.terra.structure.StructureInfo", "com.dfsek.converter.dummy.DummyInfo");
        converter.put("com.dfsek.terra.structure.serialize.SerializableBlockData", "com.dfsek.converter.dummy.DummyBlockData");
        converter.put("com.dfsek.terra.structure.StructureContainedBlock", "com.dfsek.converter.dummy.DummyBlock");
        converter.put("com.dfsek.terra.structure.StructureContainedBlock$Pull", "com.dfsek.converter.dummy.DummyBlock$Pull");
        converter.put("[[[Lcom.dfsek.terra.structure.StructureContainedBlock;", "[[[Lcom.dfsek.converter.dummy.DummyBlock;");
        converter.put("[[Lcom.dfsek.terra.structure.StructureContainedBlock;", "[[Lcom.dfsek.converter.dummy.DummyBlock;");
        converter.put("[Lcom.dfsek.terra.structure.StructureContainedBlock;", "[Lcom.dfsek.converter.dummy.DummyBlock;");

        Path working = Paths.get(".");

        System.out.println("Working directory: " + working.toFile().getAbsolutePath());

        Files.walk(working)
                .filter(file -> file.toFile().getName().endsWith(".tstructure")).forEach(path -> {
            File file = path.toFile();
            System.out.println("Converting " + file.getName());

            try(MovedObjectInputStream inputStream = new MovedObjectInputStream(new FileInputStream(file), converter)) {
                Object o = inputStream.readObject();
                DummyStructure structure = (DummyStructure) o;

                System.out.println("\nLoaded structure: " + structure.getId());
                System.out.println("Converting structure to TerraScript...");

                String script = buildStructure(structure);

                String oldName = working.toUri().relativize(file.toURI()).getPath(); // get relative path (to preserve structure)

                String newName = oldName.substring(0, oldName.length() - 10) + "tesf";
                File output = new File(working.toFile(), "out" + File.separator + newName);
                if(!output.exists()) {
                    output.getParentFile().mkdirs();
                    output.createNewFile();
                }

                try(BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
                    writer.write(script);
                }
                System.out.println("Converted to TerraScript. Saving structure to " + output.getAbsolutePath());
            } catch(ClassNotFoundException | IOException e) {
                System.err.println("Failed to convert structure!");
                e.printStackTrace();
                System.err.println("Please report this issue.");
            }
        });
    }

    public static String buildStructure(DummyStructure structure) {
        StringBuilder scriptBuilder = new StringBuilder("id \"" + structure.getId() + "\";\n\n");

        scriptBuilder.append("num y = 0;\n");

        if(!structure.getSpawns().isEmpty()) {
            StringBuilder spawns = new StringBuilder("for(y; y > -50; y = y - 1) {");
            for(DummyBlock spawn : structure.getSpawns()) {
                spawns.append("\n    if(check(").append(spawn.getX() - structure.getStructureInfo().getCenterX())
                        .append(", y + ").append(spawn.getY()).append(", ")
                        .append(spawn.getZ() - structure.getStructureInfo().getCenterZ()).append(") != ")
                        .append("\"").append(spawn.getRequirement()).append("\") {\n        continue;\n    }");
            }
            spawns.append("\n    break;");
            spawns.append("\n}\n");

            spawns.append("if(y <= -50) {\n    fail;\n}\n");
            scriptBuilder.append(spawns);
        }


        for(DummyBlock[][] blocks : structure.getStructure()) {
            for(DummyBlock[] blocks1 : blocks) {
                for(DummyBlock block : blocks1) {
                    if(!block.getBl().getData().equals("minecraft:structure_void")) {
                        String data = block.getBl().getData();
                        scriptBuilder.append("block(").append(block.getX() - structure.getStructureInfo().getCenterX())
                                .append(", y + ").append(block.getY()).append(", ")
                                .append(block.getZ() - structure.getStructureInfo().getCenterZ())
                                .append(", \"").append(data)
                                .append("\");\n");
                    }
                }
            }
        }
        return scriptBuilder.toString();
    }
}
