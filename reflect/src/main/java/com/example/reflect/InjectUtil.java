package com.example.reflect;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther: yanguoqing
 * @Date: 2023/7/27 11:46
 * @Description:
 */
public class InjectUtil {
    public static void injectEvent(Activity activity){
        if(activity == null){
            return;
        }
        final Class<? extends Activity> aClass = activity.getClass();
        final Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            final Annotation[] declaredAnnotations = declaredMethod.getDeclaredAnnotations();
            for (Annotation declaredAnnotation : declaredAnnotations) {
                final Class<? extends Annotation> annotationType = declaredAnnotation.annotationType();
                if(annotationType.isAnnotationPresent(EventType.class)){
                    final EventType annotation = annotationType.getAnnotation(EventType.class);
                    final String listenerSetter = annotation.listenerSetter();
                    final Class listenerType = annotation.listenerType();
                    try {
                        final Method value = annotationType.getDeclaredMethod("value");
                        final int[] viewIds = (int[])value.invoke(declaredAnnotation);
                        final Object proxyInstance = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{listenerType},
                                new InvocationHandler() {
                                    @Override
                                    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                                        return declaredMethod.invoke(activity, objects);
                                    }
                                });

                        for (int viewId : viewIds) {
                            final View view = activity.findViewById(viewId);
                            final Method method = view.getClass().getMethod(listenerSetter);
                            method.invoke(view,proxyInstance);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }


    }

}
