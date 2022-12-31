package com.gmail.dimabah.javapro.homeworks.third.tasks.first;

import java.lang.annotation.*;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Test {
    int a();
    int b();
}
