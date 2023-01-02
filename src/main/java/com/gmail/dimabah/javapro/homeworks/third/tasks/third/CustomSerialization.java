package com.gmail.dimabah.javapro.homeworks.third.tasks.third;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CustomSerialization {
    public String toString(Object obj) throws IllegalAccessException,
            InvocationTargetException, NoSuchMethodException, InstantiationException {
        StringBuilder sb = new StringBuilder("{");
        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (var field : fields) {
            if (field.isAnnotationPresent(Save.class)) {
                field.trySetAccessible();
                getFieldInfo(field, obj, sb);
            }
        }
        if (sb.length() > 2) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("}");
        return sb.toString();
    }

    private void getFieldInfo(Field field, Object obj, StringBuilder sb)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException, InstantiationException {
        sb.append("\"").append(field.getName()).append("\": ");
        String type = getType(field);
        int end = 1;
        if (field.getType().isArray() && field.get(obj)!=null ) {

            end = Array.getLength(field.get(obj));
            sb.append("[").append(end).append(" ");
        }
        for (int i = 0; i < end; i++) {
            sb.append("\"");
            getFieldValue(type, field, obj, i, sb);
            sb.append("\"");
            if (end == 1 || i != end - 1) {
                sb.append(", ");
            }
        }
        if (end != 1) {
            sb.append("], ");
        }
    }

    private String getType(Field field) {
        String type;
        if (field.getType().isArray()) {
            type = field.getType().componentType().getSimpleName();
        } else if (field.getType().isEnum()) {
            type = "Enum";
        } else {
            type = field.getType().getSimpleName();
        }
        return type;
    }

    private void getFieldValue(String type, Field field, Object obj, int index,
                               StringBuilder sb) throws IllegalAccessException,
            NoSuchMethodException, InvocationTargetException, InstantiationException {
        switch (type) {
            case "int", "byte", "long", "short", "Integer", "Byte", "Long", "Short",
                    "float", "double", "Float", "Double",
                    "char", "Character",
                    "boolean", "Boolean",
                    "String", "Enum" -> {
                if (field.getType().isArray()&&field.get(obj)!=null) {
                    sb.append(Array.get(field.get(obj), index));
                } else {
                    sb.append(field.get(obj));
                }
            }
            default -> {
                sb.append("{");
                Object object = field.getType().isArray() && field.get(obj)!=null
                        ? Array.get(field.get(obj), index)
                        : field.get(obj);
                if (object == null) {
                    Constructor<?> constructor = field.getType().componentType().getConstructor();
                    object = constructor.newInstance();
                }
                    for (var f : object.getClass().getDeclaredFields()) {
                        f.trySetAccessible();
                        getFieldInfo(f, object, sb);
                    }
                    sb.delete(sb.length() - 2, sb.length());
                sb.append("}");
            }
        }
    }
}
