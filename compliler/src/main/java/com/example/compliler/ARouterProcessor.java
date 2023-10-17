package com.example.compliler;

import com.example.annotation.MyARouter;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

/**
 * @Auther: yanguoqing
 * @Date: 2023/8/16 00:57
 * @Description:
 */
@AutoService(Processor.class)//启用服务
@SupportedAnnotationTypes({"com.example.annotation.MyARouter"}) //作用与哪个注解
@SupportedSourceVersion(SourceVersion.RELEASE_17) //环境版本
@SupportedOptions("student")
public class ARouterProcessor extends AbstractProcessor {
    //操作Element的工具类（类，函数，属性，其实都是Element）
    private Elements elementsTool;
    //type(类信息)的工具类，包含用于操作TypeMirror 的工具方法 (比如类的继承关系)
    private Types typesTool;
    //用来打印日志相关信息
    private Messager messager;
    //文件生成器，类、资源等，就是最终奥生成的文件， 是需要Filer来完成。
    private Filer filer;


    //在编译的时候执行,(注意：如果有使用声明的注解MyARouter时，才会执行此函数)
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager.printMessage(Diagnostic.Kind.NOTE,"====process() --annotations:" +annotations.toString());
        if(annotations.isEmpty()){
            return false;
        }
        final Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(MyARouter.class);
        messager.printMessage(Diagnostic.Kind.NOTE,"=========elements:" + elements.toString());
        for (Element element :elements){
            final String className = element.getSimpleName().toString();
            messager.printMessage(Diagnostic.Kind.NOTE,"是有MyARouter注解的类：" +className);
            //要生成的类名
            String finalClassName = className + "$$$ARouter";

            //包信息
            final String packageName = elementsTool.getPackageOf(element).getQualifiedName().toString();

            final MyARouter myARouter = element.getAnnotation(MyARouter.class);
            //方法
            final MethodSpec findTargetClass = MethodSpec.methodBuilder("findTargetClass")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(Class.class)
                    .addParameter(String.class, "path")
                    .addStatement("return path.equals($S) ? $T.class : null",
                            myARouter.path(),
                            ClassName.get((TypeElement) element))//需要Javapoet 包装转型
                    .build();
            //类
            final TypeSpec myClass = TypeSpec.classBuilder(finalClassName)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(findTargetClass)
                    .build();

            //包
            final JavaFile javaFile = JavaFile.builder(packageName, myClass)
                    .build();

            //生成Java文件
            try {
                javaFile.writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
                messager.printMessage(Diagnostic.Kind.NOTE,"生成" + finalClassName + "文件失败");
            }

           /* package com.example.helloworld;

            public final class HelloWorld {
                public static void main(String[] args) {
                    System.out.println("Hello, JavaPoet!");
                }
            }*/
            /*//1，函数
            final MethodSpec methodSpec = MethodSpec.methodBuilder("main")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(void.class)
                    .addParameter(String[].class, "args")
                    .addStatement("$T.out.println($S)", System.class, "hello javapoet!")
                    .build();

            //2,类
            final TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                    .addMethod(methodSpec)
                    .build();

            //3，包
            final JavaFile javaFile = JavaFile.builder("com.example.helloworld", helloWorld)
                    .build();
            try {
                javaFile.writeTo(filer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
*/
        }
        return false;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elementsTool = processingEnv.getElementUtils();
        typesTool = processingEnv.getTypeUtils();
        messager =processingEnv.getMessager();
        filer = processingEnv.getFiler();
        final String value = processingEnv.getOptions().get("student");
        messager.printMessage(Diagnostic.Kind.NOTE,"===================="+value);
    }
}
