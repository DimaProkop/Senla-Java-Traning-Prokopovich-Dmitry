package com.training.senla;

import com.training.senla.facade.Facade;
import com.training.senla.facade.impl.FacadeImpl;

import java.io.IOException;

/**
 * Created by prokop on 12.10.16.
 */


public class Main {

    private final static String FILE_PATH = "/home/prokop/Senla-Java-Traning-Prokopovich-Dmitry/lesson5/facade/resource/main.txt";
    public static void main(String[] args) throws IOException {
        Facade facade = new FacadeImpl();
        facade.init(FILE_PATH);
    }
}
