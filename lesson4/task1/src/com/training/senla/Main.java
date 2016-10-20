package com.training.senla;

import com.danco.training.TextFileWorker;
import com.training.senla.facade.Facade;
import com.training.senla.facade.impl.FacadeImpl;

import java.io.IOException;

/**
 * Created by prokop on 12.10.16.
 */


public class Main {

    private final static String FILE_PATH = "/home/prokop/Senla-Java-Traning-Prokopovich-Dmitry/lesson4/task1/resource/main.txt";

    public static void main(String[] args) throws IOException {
        TextFileWorker textFileWorker = new TextFileWorker(FILE_PATH);
        Facade facade = new FacadeImpl();
        facade.init(textFileWorker);



    }
}
