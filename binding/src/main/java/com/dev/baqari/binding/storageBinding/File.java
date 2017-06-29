package com.dev.baqari.binding.storageBinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface File {
    String fileName () default "no_file";
    Storage storage() default Storage.INTERNAL;
}
