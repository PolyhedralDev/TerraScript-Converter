package com.dfsek.converter.formats.iris;

import com.dfsek.converter.Converter;
import com.dfsek.converter.ScriptBuilder;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.*;

public class IrisConverter implements Converter {

    @Override
    public String convert(InputStream in) {
        try {
            // Convert input to bytes to be read multiple times
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copy(in, baos);
            byte[] bytes = baos.toByteArray();
            String script;
            try {
                script = convertV2(new ByteArrayInputStream(bytes));
            } catch (IOException e) {
                System.out.println("Reading legacy iob..");
                script = convertLegacy(new ByteArrayInputStream(bytes));
            }
            return script;
        } catch (IOException e) {
            System.err.println("Could not convert Iris object:");
            e.printStackTrace();
        }
        return null;
    }

    public static String convertV2(InputStream in) throws IOException {
        ScriptBuilder builder = new ScriptBuilder();
        DataInputStream dataIn = new DataInputStream(in);
        int w = dataIn.readInt();
        int h = dataIn.readInt();
        int d = dataIn.readInt();
        System.out.println("W: " + w + ", H: " + h + ", D: " + d);
        String format = dataIn.readUTF();
        if(!format.equals("Iris V2 IOB;"))
        {
            throw new IOException("Not V2 Format");
        }

        int blockDataCount = dataIn.readShort();
        List<String> blockData = new ArrayList<>();
        for(int i = 0; i < blockDataCount; i++) blockData.add(dataIn.readUTF());

        int blockCount = dataIn.readInt();
        for(int i = 0; i < blockCount; i++)
        {
            int x = dataIn.readShort();
            int y = dataIn.readShort();
            int z = dataIn.readShort();
            String data = blockData.get(dataIn.readShort());
            builder.block(x, y, z, data);
        }

        /* I believe this is for tile entity data, so we won't worry about it
        int stateCount = dataIn.readInt();
        for(int i = 0; i < stateCount; i++)
        {
            int x = dataIn.readShort();
            int y = dataIn.readShort();
            int z = dataIn.readShort();
            String state = dataIn.readUTF();
        }
         */
        return builder.build();
    }

    public static String convertLegacy(InputStream in) throws IOException {
        DataInputStream din = new DataInputStream(in);
        ScriptBuilder builder = new ScriptBuilder();

        int width = din.readInt();
        int height = din.readInt();
        int depth = din.readInt();
        System.out.println("WIDTH: " + width + ", HEIGHT: " + height + ", DEPTH: " + depth);
        int blockCount = din.readInt();
        for(int i = 0; i < blockCount; i++)
        {
            int x = din.readShort();
            int y = din.readShort();
            int z = din.readShort();
            String data = din.readUTF();
            builder.block(x, y, z, data);
        }

        /*
        try {
            int stateCount = din.readInt();
            System.out.println(stateCount);
            for(int i = 0; i < stateCount; i++)
            {
                int x = din.readShort();
                int y = din.readShort();
                int z = din.readShort();
                String state = din.readUTF();
            }
        } catch (Throwable ignored) {} // No state data
         */
        return builder.build();
    }
}
