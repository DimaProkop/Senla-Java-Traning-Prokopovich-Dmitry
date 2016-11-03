package com.training.senla.print;

import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;

import java.util.List;

/**
 * Created by prokop on 23.10.16.
 */
public class PrintMenu {
    public static void print(Menu menu) {

        System.out.println(menu.getName());
        List<Item> child = menu.getChild();
        int count = 1;
        for (Item item : child) {
            System.out.println(count + ". " + item.getName());
            count++;
        }
    }
}
