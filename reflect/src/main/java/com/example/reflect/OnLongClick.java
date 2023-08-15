package com.example.reflect;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.IntegerRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: yanguoqing
 * @Date: 2023/7/27 10:56
 * @Description:
 */
@EventType(listenerSetter = "setOnLongClickListener",listenerType = View.OnLongClickListener.class)
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OnLongClick {
    int[] value();
}
