package com.training.senla.invoker;

import com.training.senla.di.DependencyInjection;
import com.training.senla.facade.Facade;
import com.training.senla.facade.impl.FacadeImpl;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by dmitry on 26.11.16.
 */
public class MethodInvoker {
    public static Object invokeMethod(String methodName, Object params) {
        Facade facade = (FacadeImpl) DependencyInjection.getInstance(Facade.class);
        Object answer = null;
        if(params != null) {
            try {
                answer = facade.getClass().getMethod(methodName, Facade.class).invoke(facade, params);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        return answer;
    }
}
