package com.gmail.dimabah.javapro.homeworks.third.tasks.second;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

@SaveTo(path = "d:\\file.txt")
public class TextContainer {
    private String text = "Default text";

    public TextContainer(String text) {
        this.text = text;
    }

    public TextContainer() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TextContainer{" +
                "text='" + text + '\'' +
                '}';
    }

    @SaveMethod
    public void save(String text, String fileName) {
        try (PrintWriter printWriter = new PrintWriter(fileName)) {
            printWriter.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
