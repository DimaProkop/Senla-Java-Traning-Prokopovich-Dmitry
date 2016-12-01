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
        Facade facade = (Facade) DependencyInjection.getInstance(Facade.class);
        facade.init();
        Object answer = null;

        try {
            if(params != null) {
                answer = facade.getClass().getMethod(methodName, Object.class).invoke(facade, params);
            }else {
                answer = facade.getClass().getMethod(methodName, new Class[] {}).invoke(facade);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return answer;
    }
}
