package com.training.senla.menu;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by prokop on 24.10.16.
 */
public class Menu{
    private String name;
    private List<Item> child;

    public Menu(String name) {
        this.name = name;
        this.child = new ArrayList<>();
    }
    

    public void addItem(Item item) {
        child.add(item);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getChild() {
        return child;
    }

    public void setChild(List<Item> child) {
        this.child = child;
    }
}
