package com.example.compliler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
@SupportedAnnotationTypes("com.example.annotation.MyClass")
public class TestProcessor extends AbstractProcessor {
    //javac 会调用此方法
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //写什么代码就做什么事
        //process()：javac编译时候一个回调
        final Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE,"====================");
        return false;
    }

    /*
     * @apiNote 注意：1.必须重写该方法：支持的注解类型
     *               2.或者在类名上添加@SupportedAnnotationTypes注解，并指定注解的全类名
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> set = new HashSet<String>();
        set.add("com.example.annotation.MyClass");
        return set;
    }
}