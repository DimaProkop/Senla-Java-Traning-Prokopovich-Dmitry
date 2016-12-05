package com.training.senla.invoke;

import com.training.senla.di.DependencyInjection;
import com.training.senla.facade.Facade;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 5.12.16.
 */
public class SecondInvoker {
    public static Object invokeMethod(String methodName, Object args) {
        Facade facade = (Facade) DependencyInjection.getInstance(Facade.class);
        facade.init();

        Object answer = null;
        List<Object> objects = (List<Object>) args;
        Object[] params = new Object[objects.size()];
        for (int i = 0; i < objects.size(); i++) {
            params[i] = objects.get(i);
        }

        Class<?>[] classes = new Class[params.length];

            try {
                if(args != null) {
                    for (int i = 0; i < params.length; i++) {
                        classes[i] = params[i].getClass();
                    }
                    answer = facade.getClass().getMethod(methodName, classes).invoke(facade, params);
                }else {
                    answer = facade.getClass().getMethod(methodName, classes).invoke(facade);
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }

        return answer;
    }
}
