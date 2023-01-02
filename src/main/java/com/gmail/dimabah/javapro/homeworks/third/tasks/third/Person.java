package com.gmail.dimabah.javapro.homeworks.third.tasks.third;

import java.util.Arrays;


public class Person {
    @Save private String name;
    @Save private int age;
    @Save private double wight;
    private int height;
    @Save private Gender gender;
    @Save private boolean live;
    @Save private char firstLetter;
    @Save private String[] preferences;

    @Save  private Parents[] parents;

    public Person(String name, int age, double wight, int height, Gender gender, boolean live,
                  char firstLetter, String[] preferences, Parents[] parents) {
        this.name = name;
        this.age = age;
        this.wight = wight;
        this.height = height;
        this.gender = gender;
        this.live = live;
        this.firstLetter = firstLetter;
        this.preferences = preferences;
        this.parents = parents;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWight() {
        return wight;
    }

    public void setWight(double wight) {
        this.wight = wight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public char getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(char firstLetter) {
        this.firstLetter = firstLetter;
    }


    public String[] getPreferences() {
        return preferences;
    }

    public void setPreferences(String[] preferences) {
        this.preferences = preferences;
    }

    public Parents[] getParents() {
        return parents;
    }

    public void setParents(Parents[] parents) {
        this.parents = parents;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", wight=" + wight +
                ", height=" + height +
                ", gender=" + gender +
                ", live=" + live +
                ", firstLetter=" + firstLetter +
                ", preferences=" + Arrays.toString(preferences) +
                ", parents=" + Arrays.toString(parents) +
                '}';
    }
}
