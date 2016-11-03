package com.training.senla;

import com.training.senla.launcher.Launcher;
import com.training.senla.menu.Builder;

import java.io.IOException;

/**
 * Created by prokop on 22.10.16.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        //ui
        Builder builder = new Builder();
        Launcher launcher = new Launcher();
        launcher.start(builder.buildMenu());
    }
}
