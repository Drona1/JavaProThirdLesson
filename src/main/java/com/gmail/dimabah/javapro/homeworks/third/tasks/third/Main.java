package com.gmail.dimabah.javapro.homeworks.third.tasks.third;

import java.io.*;
import java.lang.reflect.*;

public class Main {

    public static void main(String[] args) {
        Person person = new Person("Usyk", 34, 100.5, 190, Gender.MALE, true, 'O',
                new String[]{"box", "sport"}, new Parents[]
                {new Parents("Nadiya", "Oleksandr"),
                        new Parents("unknown", "unknown")});
        try {
            System.out.println("Object:");
            System.out.println(person + System.lineSeparator());
            CustomSerialization customSer = new CustomSerialization();
            String serObject = customSer.toString(person, true);
            saveToFile("object.ser", serObject);
            System.out.println("Object as a string:");
            System.out.println(serObject + System.lineSeparator());
            System.out.println("Object before change:");
            Person person2 = new Person("unknown", 20, 50, 150, Gender.FEMALE,
                    true, 'X', new String[]{"first", "second", "third"}, null);
            System.out.println(person2);
            CustomDeserialization customDeserialization = new CustomDeserialization();
            customDeserialization.changeObject(person2, readFromFile("object.ser"));
            System.out.println(System.lineSeparator() + "Object after change:");
            System.out.println(person2);
            System.out.println("Object from string:");
            Person person3 = new CustomDeserialization()
                    .fromStringToObject(Person.class, readFromFile("object.ser"));
            System.out.println(person3);
        } catch (IllegalAccessException | NoSuchFieldException
                 | InvocationTargetException | NoSuchMethodException
                 | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private static void saveToFile(String fileName, String text) {
        try (PrintWriter printWriter = new PrintWriter(fileName)) {
            printWriter.print(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String readFromFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader fileIn = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = fileIn.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}








