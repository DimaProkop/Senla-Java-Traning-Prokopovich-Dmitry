package com.training.senla;

import com.training.senla.facade.Facade;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.launcher.Launcher;
import com.training.senla.menu.Builder;

/**
 * Created by prokop on 22.10.16.
 */
public class Main {

    public static void main(String[] args) {
        Facade facade = new FacadeImpl();
        Builder builder = new Builder(facade);
        Launcher launcher = new Launcher();
        launcher.start(builder.buildMenu());
    }
}
