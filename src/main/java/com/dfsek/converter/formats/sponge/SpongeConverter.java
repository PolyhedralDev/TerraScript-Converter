package com.dfsek.converter.formats.sponge;

import com.dfsek.converter.Converter;
import com.dfsek.converter.ScriptBuilder;
import net.querz.nbt.io.NBTDeserializer;
import net.querz.nbt.io.NBTUtil;
import net.querz.nbt.io.NamedTag;
import net.querz.nbt.tag.ArrayTag;
import net.querz.nbt.tag.ByteArrayTag;
import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.IntArrayTag;
import net.querz.nbt.tag.IntTag;
import net.querz.nbt.tag.Tag;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class SpongeConverter implements Converter {
    @Override
    public String convert(InputStream in) {
        try {
            CompoundTag baseTag = (CompoundTag) new NBTDeserializer(false).fromStream(detectDecompression(in)).getTag();
            int wid = baseTag.getShort("Width");
            int len = baseTag.getShort("Length");
            int hei = baseTag.getShort("Height");
            System.out.println("WIDTH: " + wid);
            System.out.println("HEIGHT: " + hei);
            System.out.println("LENGTH: " + len);

            ByteArrayTag blocks = baseTag.getByteArrayTag("BlockData");

            CompoundTag palette = (CompoundTag) baseTag.get("Palette");
            Map<Integer, String> data = new HashMap<>();

            for(Map.Entry<String, Tag<?>> entry : palette.entrySet()) {
                data.put(((IntTag) entry.getValue()).asInt(), entry.getKey());
            }

            byte[] arr =  blocks.getValue();
            ScriptBuilder builder = new ScriptBuilder();
            for(int x = 0; x < hei; x++) {
                for(int y = 0; y < wid; y++) {
                    for(int z = 0; z < len; z++) {
                        String block = data.get((int) arr[x+z*wid+y*wid*len]);
                        if(block.startsWith("minecraft:structure_void")) continue;
                        builder.block(x, y, z, block);
                    }
                }
            }

            return builder.build();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static InputStream detectDecompression(InputStream is) throws IOException {
        PushbackInputStream pbis = new PushbackInputStream(is, 2);
        int signature = (pbis.read() & 0xFF) + (pbis.read() << 8);
        pbis.unread(signature >> 8);
        pbis.unread(signature & 0xFF);
        if (signature == GZIPInputStream.GZIP_MAGIC) {
            return new GZIPInputStream(pbis);
        }
        return pbis;
    }
}
