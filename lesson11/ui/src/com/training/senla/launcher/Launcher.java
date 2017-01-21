package com.training.senla.launcher;

import com.training.senla.menu.Menu;
import com.training.senla.menu.Item;
import com.training.senla.print.PrintMenu;
import com.training.senla.reader.Reader;
import com.training.senla.service.RequestHandler;

/**
 * Created by prokop on 24.10.16.
 */
public class Launcher {
    private RequestHandler requestHandler;

    public Launcher(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public void start(Menu menu) {
        while (menu != null) {
            PrintMenu.print(menu);
            int level = Reader.getInt("Level: ");
            Item item = menu.getChild().get(level - 1);
            item.getAction().execute(requestHandler);
            menu = item.getMenu();
            if (menu == null) {
                requestHandler.disconnect();
            }
        }
    }
}
