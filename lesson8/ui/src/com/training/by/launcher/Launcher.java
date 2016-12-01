package com.training.by.launcher;

import com.training.by.menu.Menu;
import com.training.senla.DataPacket;
import com.training.senla.RequestHandler;
import com.training.by.menu.Item;
import com.training.by.print.PrintMenu;
import com.training.by.reader.Reader;

/**
 * Created by prokop on 24.10.16.
 */
public class Launcher {
    private RequestHandler requestHandler;
    public Launcher(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public void start(Menu menu) {
        while (menu !=null) {
            PrintMenu.print(menu);
            int level = Reader.getInt("Level: ");
            Item item = menu.getChild().get(level - 1);
            item.getAction().execute(requestHandler);
            menu = item.getMenu();
            if(menu == null) {
                requestHandler.disconnect();
            }
        }
        saveDataAfterExit();
    }

    private void saveDataAfterExit() {
        DataPacket packet = new DataPacket("exportAll", null);
        requestHandler.sendRequest(packet);
    }
}
