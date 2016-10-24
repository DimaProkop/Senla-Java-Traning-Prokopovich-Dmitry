package com.training.senla.menu;

import java.util.List;
/**
 * Created by prokop on 24.10.16.
 */
public class Menu{
    private String name;
    private List<Item> child;
    private List<Item> parent;

    public void addItem(Item item) {
        child.add(item);
    }

    public Menu(String name) {
        this.name = name;
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

    public List<Item> getParent() {
        return parent;
    }

    public void setParent(List<Item> parent) {
        this.parent = parent;
    }
}
