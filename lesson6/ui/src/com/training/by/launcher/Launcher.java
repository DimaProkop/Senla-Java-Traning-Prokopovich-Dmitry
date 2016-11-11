package com.training.by.launcher;

import com.training.by.menu.Menu;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.by.menu.Item;
import com.training.by.print.PrintMenu;
import com.training.by.reader.Reader;

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
        FacadeImpl.getInstance().exportAll();
    }
}
