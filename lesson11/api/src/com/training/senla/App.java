package com.training.senla;

import com.training.senla.invoker.MethodInvoker;

/**
 * Created by dmitry on 20.1.17.
 */
public class App {
    public static void main(String[] args) {
        Object obj = MethodInvoker.invokeMethod("getAllGuests", null);
        int i = 1 + 1;
    }
}
