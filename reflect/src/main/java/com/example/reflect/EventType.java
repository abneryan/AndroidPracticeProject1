package com.example.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: yanguoqing
 * @Date: 2023/7/27 10:58
 * @Description:
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EventType {
    String listenerSetter();
    Class  listenerType();
}
