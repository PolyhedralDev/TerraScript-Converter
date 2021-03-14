package com.dfsek.converter;

import java.util.ArrayList;
import java.util.List;

public class ScriptBuilder {
    private final List<String> blockFunctions = new ArrayList<>();
    private final List<String> checkFunctions = new ArrayList<>();

    public ScriptBuilder block(int x, int y, int z, String data) {
        blockFunctions.add("block(" + x + ", " + y + ", " + z + ", \"" + data + "\");\n");
        return this;
    }

    public ScriptBuilder check(int x, int y, int z, String type) {
        checkFunctions.add("\tif(check(" + x + ", y + " + y + ", " + z + ") != \"" + type + "\") continue;\n");
        return this;
    }

    public String build() {
        StringBuilder builder = new StringBuilder();
        builder.append("num y = 0;\n");
        if(!checkFunctions.isEmpty()) {
            builder.append("for(y; y > -50; y = y - 1) {\n");
            builder.append("\ty = y + 1;\n");
            checkFunctions.forEach(builder::append);
            builder.append("\tbreak;\n");
            builder.append("}\n");
            builder.append("if(y <= -50) continue;\n");
        }

        blockFunctions.forEach(builder::append);
        return builder.toString();
    }
}
