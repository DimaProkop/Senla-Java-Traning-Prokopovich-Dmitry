package com.training.senla.invoker;


import com.training.senla.service.FacadeService;
import com.training.senla.service.impl.FacadeServiceImpl;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by dmitry on 26.11.16.
 */
public class MethodInvoker {
    public static Object invokeMethod(String methodName, Object params) {
        FacadeService facade = new FacadeServiceImpl();
        Object answer = null;

        try {
            if(params != null) {
                answer = facade.getClass().getMethod(methodName, Object.class).invoke(facade, params);
            }else {
                answer = facade.getClass().getMethod(methodName, new Class[] {}).invoke(facade);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.getCause().printStackTrace();
        }

        return answer;
    }
}
