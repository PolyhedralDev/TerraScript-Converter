package com.dfsek.converter.formats.rwg;

import com.dfsek.tectonic.annotations.Value;
import com.dfsek.tectonic.config.ConfigTemplate;

import java.util.Map;

public class RWGTemplate implements ConfigTemplate {
    @Value("blocks")
    private Map<String, String> blocks;


    public Map<String, String> getBlocks() {
        return blocks;
    }
}
