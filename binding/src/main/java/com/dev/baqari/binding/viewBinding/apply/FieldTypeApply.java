package com.dev.baqari.binding.viewBinding.apply;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import com.dev.baqari.binding.viewBinding.annotation.storage.File;
import com.dev.baqari.binding.viewBinding.annotation.storage.Preference;
import com.dev.baqari.binding.viewBinding.annotation.storage.Storage;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;


public class FieldTypeApply {
    @SuppressLint("StaticFieldLeak")
    public static Context context;

    public static void apply(Object object, Field field, Preference preference) {
        String parameter = field.getAnnotation(Preference.class).forName();
        SharedPreferences reference = FieldTypeApply.context.getSharedPreferences(parameter, Context.MODE_PRIVATE);
        try {
            field.setAccessible(true);
            field.set(object, reference);
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void apply(Object object, Field field, File preference) {
        String parameter = field.getAnnotation(File.class).fileName();
        Storage storage = field.getAnnotation(File.class).storage();
        java.io.File reference = null;
        if (storage == Storage.EXTERNAL) {
            java.io.File sdcard = Environment.getExternalStorageDirectory();
            reference = new java.io.File(sdcard, parameter);
        } else {
            reference = new java.io.File(context.getFilesDir() + "/");
        }
        if (reference == null)
            try {
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        try {
            field.setAccessible(true);
            field.set(object, reference);
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
