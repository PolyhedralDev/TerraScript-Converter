package com.dfsek.converter;

import com.dfsek.converter.formats.ewg.EWGConverter;
import com.dfsek.converter.formats.iris.IrisConverter;
import com.dfsek.converter.formats.rwg.RWGConverter;
import com.dfsek.converter.formats.sponge.SpongeConverter;
import com.dfsek.converter.formats.tstructure.TStructureConverter;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

public class ScriptConverter {
    private static final Path working = Paths.get(".");
    public static void main(String[] args) throws IOException {
        System.out.println("Working directory: " + working.toFile().getAbsolutePath());
        walk("tstructure", new TStructureConverter());
        walk("ewg", new EWGConverter());
        walk("rwgfast", new RWGConverter());
        walk("schem", new SpongeConverter());
        walk("iob", new IrisConverter());
    }

    private static void walk(String extension, Converter converter) throws IOException {
        Files.walk(working).filter(file -> file.toFile().getName().toLowerCase(Locale.ROOT).endsWith("." + extension)).forEach(path -> {
            File file = path.toFile();
            System.out.println("Converting " + file.getAbsolutePath());
            File out = new File(working.toFile(), "out" + File.separator + extension + File.separator + file.getName() + ".tesf");
            try {
                if(!out.exists()) {
                    out.getParentFile().mkdirs();
                    out.createNewFile();
                }
                String data = converter.convert(new FileInputStream(file));
                String id = file.getName();
                id = id.substring(0, id.lastIndexOf('.'));
                data = "id \"" + id + "\";\n\n" + data;
                IOUtils.write(data, new FileOutputStream(out), Charset.defaultCharset());
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Saved to " + out.getAbsolutePath());
        });
    }
}
