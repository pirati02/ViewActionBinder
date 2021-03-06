package com.dev.baqari.binding.viewBinding.annotation.storage;

import com.dev.baqari.binding.Constant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface File {
    String fileName () default Constant.FILE_NAME;
    Storage storage() default Storage.INTERNAL;
}
