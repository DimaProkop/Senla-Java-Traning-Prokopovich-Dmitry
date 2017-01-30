package com.training.senla;

import com.training.senla.invoker.MethodInvoker;
import com.training.senla.model.GuestModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 20.1.17.
 */
public class App {
    public static void main(String[] args) {
        GuestModel g = new GuestModel("s");
        List objects = new ArrayList<Object>();
        objects.add(g);
        Object ob = MethodInvoker.invokeMethod("getAllGuests", null);
        Object obj = MethodInvoker.invokeMethod("addGuest", objects);
        Object ob1 = MethodInvoker.invokeMethod("getAllGuests", null);
        int i = 1 + 1;
    }
}
