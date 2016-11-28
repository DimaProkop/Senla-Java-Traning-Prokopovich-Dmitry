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
        if(params != null) {
            try {
                answer = facade.getClass().getMethod(methodName, Object.class).invoke(facade, params);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }else {
            try {
                try {
                    answer = facade.getClass().getMethod(methodName, new Class[] {}).invoke(facade);
                } catch (InvocationTargetException e) {
                    System.err.println("An InvocationTargetException was caught!");
                    Throwable cause = e.getCause();
                    System.out.format("Invocation of %s failed because of: %s%n",
                            methodName, cause.getMessage());

                }
            } catch (IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        return answer;
    }
}
