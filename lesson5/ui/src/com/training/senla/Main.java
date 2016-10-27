package com.training.senla;

import com.danco.training.TextFileWorker;
import com.training.senla.facade.Facade;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.launcher.Launcher;
import com.training.senla.menu.Builder;

import java.io.IOException;

/**
 * Created by prokop on 22.10.16.
 */
public class Main {
    private final static String FILE_PATH = "/home/prokop/Senla-Java-Traning-Prokopovich-Dmitry/lesson5/facade/resource/main.txt";
    public static void main(String[] args) throws IOException {

        //facade
        TextFileWorker textFileWorker = new TextFileWorker(FILE_PATH);
        Facade facade = new FacadeImpl();
        facade.init(textFileWorker);

        //ui
        Builder builder = new Builder(facade);
        Launcher launcher = new Launcher();
        launcher.start(builder.buildMenu());
    }
}
