package com.hadas.krzysztof.models;

import java.util.List;

public class Result<T> {

    private List<T> items;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
