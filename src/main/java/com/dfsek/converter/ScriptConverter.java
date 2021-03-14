package com.dfsek.converter;

import com.dfsek.converter.formats.ewg.EWGConverter;
import com.dfsek.converter.formats.rwg.RWGConverter;
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
    }

    private static void walk(String extension, Converter converter) throws IOException {
        Files.walk(working).filter(file -> file.toFile().getName().toLowerCase(Locale.ROOT).endsWith("." + extension)).forEach(path -> {
            File file = path.toFile();
            System.out.println("Converting " + file.getName());
            File out = new File(working.toFile(), "out" + File.separator + extension + File.separator + file.getName() + ".tesf");
            try {
                if(!out.exists()) {
                    out.getParentFile().mkdirs();
                    out.createNewFile();
                }
                IOUtils.write(converter.convert(new FileInputStream(file)), new FileOutputStream(out), Charset.defaultCharset());
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
