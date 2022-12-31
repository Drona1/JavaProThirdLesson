package com.gmail.dimabah.javapro.homeworks.third.tasks.first;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        try {
            callTestMethod();
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    private static void callTestMethod() throws InvocationTargetException, IllegalAccessException {
        ExampleOfReflection example = new ExampleOfReflection();
        Class<?> cls = example.getClass();
        Method[] methods = cls.getDeclaredMethods();
        for (var method :methods) {
            if (method.isAnnotationPresent(Test.class)){
                Test annotation = method.getAnnotation(Test.class);
                method.invoke(example,annotation.a(),annotation.b());
            }
        }
    }
}

class ExampleOfReflection {
    ExampleOfReflection() {
    }
    @Test(a=2, b=5)
    public void test(int a, int b) {
        System.out.println("a = " + a + " b = " + b);
    }
}