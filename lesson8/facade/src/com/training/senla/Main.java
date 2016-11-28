package com.training.senla;

import com.training.senla.di.DependencyInjection;
import com.training.senla.facade.Facade;
import com.training.senla.invoker.MethodInvoker;

import java.io.IOException;

/**
 * Created by prokop on 12.10.16.
 */


public class Main {

    public static void main(String[] args) throws IOException {
        Facade facade = (Facade) DependencyInjection.getInstance(Facade.class);
        facade.init();
        Object check = MethodInvoker.invokeMethod("getAllGuests", null);

    }
}
