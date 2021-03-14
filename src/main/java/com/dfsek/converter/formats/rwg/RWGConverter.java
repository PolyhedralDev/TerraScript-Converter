package com.dfsek.converter.formats.rwg;

import com.dfsek.converter.Converter;
import com.dfsek.converter.ScriptBuilder;
import com.dfsek.tectonic.exception.ConfigException;
import com.dfsek.tectonic.loading.ConfigLoader;

import java.io.InputStream;

public class RWGConverter implements Converter {
    @Override
    public String convert(InputStream in) {
        ConfigLoader loader = new ConfigLoader();

        RWGTemplate template = new RWGTemplate();
        try {
            loader.load(template, in);
        } catch(ConfigException e) {
            System.err.println("Unable to convert RWG structure:");
            e.printStackTrace();
        }
        ScriptBuilder builder = new ScriptBuilder();
        template.getBlocks().forEach((coord, block) -> {
            String[] coords = coord.split("_");
            builder.block(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]), block);
        });
        return builder.build();
    }
}
