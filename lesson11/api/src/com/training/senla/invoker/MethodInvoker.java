package com.training.senla.invoker;


import com.training.senla.di.DependencyInjection;
import com.training.senla.facade.Facade;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by dmitry on 26.11.16.
 */
public class MethodInvoker {
    public static Object invokeMethod(String methodName, List<Object> args) {
        Facade facade = (Facade) DependencyInjection.getInstance(Facade.class);
        facade.init();

        Object answer = null;

        try {
            if (args != null) {
                Object[] params = new Object[args.size()];
                for (int i = 0; i < args.size(); i++) {
                    params[i] = args.get(i);
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
