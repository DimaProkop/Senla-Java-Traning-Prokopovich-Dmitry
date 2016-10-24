package com.training.senla.launcher;

import com.training.senla.menu.Menu;
import com.training.senla.print.PrintMenu;

/**
 * Created by prokop on 24.10.16.
 */
public class Launcher {
    public Launcher() {
    }

    public void start(Menu menu) {
        while (menu !=null) {
            //print current menu
            PrintMenu.print(menu);
        }
    }
}
