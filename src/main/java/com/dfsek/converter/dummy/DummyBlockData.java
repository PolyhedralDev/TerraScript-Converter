package com.dfsek.converter.dummy;

import java.io.Serializable;

public class DummyBlockData implements Serializable {
    private static final long serialVersionUID = 5298928608478640008L;
    private final String data;

    public DummyBlockData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
