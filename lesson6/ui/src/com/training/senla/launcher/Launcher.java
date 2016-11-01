package com.training.senla.launcher;

import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.print.PrintMenu;
import com.training.senla.reader.Reader;

/**
 * Created by prokop on 24.10.16.
 */
public class Launcher {
    public Launcher() {
    }

    public void start(Menu menu) {
        while (menu !=null) {
            PrintMenu.print(menu);
            int level = Reader.getInt("Level: ");
            Item item = menu.getChild().get(level - 1);
            item.getAction().execute();
            menu = item.getMenu();
        }
    }
}
