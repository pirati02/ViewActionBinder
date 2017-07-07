package com.dev.baqari.binding.viewBinding.annotation.component;


import com.dev.baqari.binding.Constant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Receiver {
    String[] actions () default Constant.BROADCAST_DEFAULT;
}
