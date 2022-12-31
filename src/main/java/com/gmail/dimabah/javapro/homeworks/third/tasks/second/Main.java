package com.gmail.dimabah.javapro.homeworks.third.tasks.second;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        try {
            new Saver().saveToFileUsingAnnAndReflect();
        } catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
