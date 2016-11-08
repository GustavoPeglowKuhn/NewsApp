package com.omega.newsapp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Omega on 2016-11-06.
 */

public class ItemList implements Serializable {
    private List<Item> list;

    public ItemList(List<Item> list) {
        this.list = list;
    }

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }
}