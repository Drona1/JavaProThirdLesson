package com.gmail.dimabah.javapro.homeworks.third.tasks.third;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CustomDeserialization {
    private int start;
    private int end;
    private int endLimit;

    public void changeObject(Object obj, String serObject) throws NoSuchFieldException,
            IllegalAccessException, InvocationTargetException, NoSuchMethodException,
            InstantiationException {
        Class<?> cls = obj.getClass();
        endLimit = serObject.length();
        Field field;
        while ((field = getField(cls, serObject)) != null) {
            setFieldValue(obj, serObject, field);
        }
    }

    private Field getField(Class<?> cls, String serObject) throws NoSuchFieldException {
        String name;
        Field field;
        start = serObject.indexOf("\"", end + 1) + 1;
        if (start == 0 || start > endLimit) {
            return null;
        }
        end = serObject.indexOf("\":", start);
        name = serObject.substring(start, end);
        field = cls.getDeclaredField(name);
        field.trySetAccessible();
        start = end + 4;
        return field;
    }

    private void setFieldValue(Object obj, String serObject, Field field) throws
            IllegalAccessException, NoSuchFieldException, InvocationTargetException,
            NoSuchMethodException, InstantiationException {
        String value;
        int arrLen;
        if (serObject.charAt(start - 1) == '[') {
            arrLen = Integer.parseInt(serObject.substring(start, serObject.indexOf(" ", start)));
            start = serObject.indexOf("\"", start) + 1;
        } else {
            arrLen = 1;
        }
        Object array = null;
        for (int index = 0; index < arrLen; index++) {
            if (start>endLimit){
                break;
            }
            end = serObject.indexOf("\"", start);
            value = serObject.substring(start, end);
            if (arrLen > 1) {
                if (index == 0) {
                    array = Array.newInstance(field.getType().componentType(), arrLen);
                }
                setValueInArray(array, field, value, serObject, index);
                if (index == arrLen - 1) {
                    field.set(obj, field.getType().cast(array));
                }
            } else {
                setValue(obj, field, value, serObject);
            }
            start = end + 4;
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

    private void setValueInArray(Object obj, Field field, String value, String serObject, int index)
            throws IllegalAccessException, NoSuchFieldException, InvocationTargetException,
            NoSuchMethodException, InstantiationException {
        String type = getType(field);
        if (value.equals("null")) {
            type = "null";
        }
        switch (type) {
            case "byte", "Byte" -> Array.set(obj, index, Byte.parseByte(value));
            case "short", "Short" -> Array.set(obj, index, Short.parseShort(value));
            case "int", "Integer" -> Array.set(obj, index, Integer.parseInt(value));
            case "long", "Long" -> Array.set(obj, index, Long.parseLong(value));
            case "double", "Double" -> Array.set(obj, index, Double.parseDouble(value));
            case "float", "Float" -> Array.set(obj, index, Float.parseFloat(value));
            case "char", "Character" -> Array.set(obj, index, value.charAt(0));
            case "boolean", "Boolean" -> Array.set(obj, index, Boolean.parseBoolean(value));
            case "Enum" -> Array.set(obj, index, Enum.valueOf((Class<Enum>) field.getType(), value));
            case "null" -> Array.set(obj, index, null);
            case "String" -> Array.set(obj, index, value);
            default -> setValueInArrayOfObjects(obj, field, serObject, index);
        }
    }

    private void setValueInArrayOfObjects(Object obj, Field field, String serObject, int index)
            throws IllegalAccessException, NoSuchFieldException, InvocationTargetException,
            NoSuchMethodException, InstantiationException {
        end = serObject.indexOf("{", end - 1);
        endLimit = serObject.indexOf("}", end);
        Object object = Array.get(obj, index);
        if (object == null) {
            Constructor<?> constructor = field.getType().componentType().getConstructor();
            object = constructor.newInstance();
            Array.set(obj, index, object);
        }
        Field field1;
        while ((field1 = getField(field.getType().componentType(), serObject)) != null) {
            setFieldValue(object, serObject, field1);
        }
        endLimit = serObject.length();
        end = end + 3;
    }

    private void setValue(Object obj, Field field, String value, String serObject)
            throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException,
            InvocationTargetException, InstantiationException {
        String type = getType(field);
        if (value.equals("null")) {
            type = "null";
        }
        switch (type) {
            case "byte", "Byte" -> field.set(obj, Byte.parseByte(value));
            case "short", "Short" -> field.set(obj, Short.parseShort(value));
            case "int", "Integer" -> field.set(obj, Integer.parseInt(value));
            case "long", "Long" -> field.set(obj, Long.parseLong(value));
            case "double", "Double" -> field.set(obj, Double.parseDouble(value));
            case "float", "Float" -> field.set(obj, Float.parseFloat(value));
            case "char", "Character" -> field.set(obj, value.charAt(0));
            case "boolean", "Boolean" -> field.set(obj, Boolean.parseBoolean(value));
            case "Enum" -> field.set(obj, Enum.valueOf((Class<Enum>) field.getType(), value));
            case "String" -> field.set(obj, value);
            case "null" -> field.set(obj, null);
            default -> setValueInObjects(obj, field, serObject);
        }
    }

    private void setValueInObjects(Object obj, Field field, String serObject)
            throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException,
            InvocationTargetException, InstantiationException {
        end = serObject.indexOf("{", end - 2);
        endLimit = serObject.indexOf("}", end);
        if (field.getType().isArray()) {
            field.set(obj, null);
            end = endLimit;
            return;
        }
        Object object = field.get(obj);
        if (object == null) {
            Constructor<?> constructor = field.getType().getConstructor();
            object = constructor.newInstance();
            field.set(obj, object);
        }
        Field field1;
        while ((field1 = getField(field.getType(), serObject)) != null) {
            setFieldValue(object, serObject, field1);
        }
        endLimit = serObject.length();
        end = end + 3;
    }
}
