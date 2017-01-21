package com.training.senla.invoker;


import com.training.senla.di.DependencyInjection;
import com.training.senla.facade.Facade;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by dmitry on 26.11.16.
 */
public class MethodInvoker {
    public static Object invokeMethod(String methodName, Object args) {
        Facade facade = (Facade) DependencyInjection.getInstance(Facade.class);
        facade.init();

        Object answer = null;

        try {
            if (args != null) {
                List<Object> objects = (List<Object>) args;
                Object[] params = new Object[objects.size()];
                for (int i = 0; i < objects.size(); i++) {
                    params[i] = objects.get(i);
                }

                Class<?>[] classes = new Class[params.length];


                for (int i = 0; i < params.length; i++) {
                    classes[i] = params[i].getClass();
                }
                answer = facade.getClass().getMethod(methodName, classes).invoke(facade, params);

            } else {
                answer = facade.getClass().getMethod(methodName, new Class[]{}).invoke(facade);
            }

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return answer;
    }
}
