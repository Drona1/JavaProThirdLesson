package com.gmail.dimabah.javapro.homeworks.third.tasks.third;

import java.io.*;
import java.lang.reflect.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Person person = new Person("Vasiliy", 34, 61.05, 170, Gender.MALE, true, 'L',
                new String[]{"box", "sport"}, new Parents[]
                {new Parents("Anatoliy", "Tetyana"),
                        new Parents("unknown", "unknown")});
        Person person2 = new Person();
        try {
            System.out.println("Object: ");
            System.out.println(person);
            CustomSerialization customSer = new CustomSerialization();
            String serObject = customSer.toString(person);
            saveToFile("object.ser",serObject);
            System.out.println();
            System.out.println("Object as a string: ");
            System.out.println(serObject);
            System.out.println();

            System.out.println("Object before change: ");
            System.out.println(person2);
            CustomDeserialization customDeserialization = new CustomDeserialization();
            customDeserialization.changeObject(person2,readFromFile("object.ser",serObject));
            System.out.println();
            System.out.println("Object after change: ");
            System.out.println(person2);
        } catch (IllegalAccessException | NoSuchFieldException | InvocationTargetException | NoSuchMethodException |
                 InstantiationException e) {
            e.printStackTrace();
        }
    }
    private static void saveToFile(String fileName, String text){
        try (PrintWriter printWriter = new PrintWriter(fileName)){
            printWriter.print(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static String readFromFile(String fileName, String text){
        StringBuilder sb = new StringBuilder();
        try(BufferedReader fileIn = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = fileIn.readLine())!=null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}








