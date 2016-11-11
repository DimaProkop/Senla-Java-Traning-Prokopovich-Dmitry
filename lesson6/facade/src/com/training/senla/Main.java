package com.training.senla;

import com.training.senla.facade.impl.FacadeImpl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by prokop on 12.10.16.
 */


public class Main {

    public static void main(String[] args) throws IOException {
        FacadeImpl.getInstance().init();
    }
}
