package com.gmail.dimabah.javapro.homeworks.third.tasks.second;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Saver {
    public void saveToFileUsingAnnAndReflect() throws NoSuchFieldException,
            IllegalAccessException, InvocationTargetException {

        TextContainer textContainer = new TextContainer();
        Class<?> cls = textContainer.getClass();

        String filePath = cls.getAnnotation(SaveTo.class).path();
        Field field = cls.getDeclaredField("text");
        field.trySetAccessible();
        String text = (String) field.get(textContainer);

        Method[] methods = cls.getMethods();

        for (var method : methods) {
            if (method.isAnnotationPresent(SaveMethod.class)) {
                method.invoke(textContainer, text, filePath);
                break;
            }
        }
    }
}
