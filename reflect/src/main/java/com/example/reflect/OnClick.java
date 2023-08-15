package com.example.reflect;

import android.view.View;

import androidx.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: yanguoqing
 * @Date: 2023/7/27 10:53
 * @Description:
 */
@EventType(listenerSetter = "setOnClickListener",listenerType = View.OnClickListener.class)
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OnClick {
     int[] value();
}
