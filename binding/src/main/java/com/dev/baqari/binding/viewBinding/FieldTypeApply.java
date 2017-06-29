package com.dev.baqari.binding.viewBinding;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;

import com.dev.baqari.binding.storageBinding.File;
import com.dev.baqari.binding.storageBinding.Preference;
import com.dev.baqari.binding.storageBinding.Storage;
import com.dev.baqari.binding.viewBinding.annotation.OnCheckChange;
import com.dev.baqari.binding.viewBinding.annotation.OnClick;
import com.dev.baqari.binding.viewBinding.annotation.OnItemClick;
import com.dev.baqari.binding.viewBinding.annotation.OnItemSelect;
import com.dev.baqari.binding.viewBinding.annotation.OnLongClick;
import com.dev.baqari.binding.viewBinding.annotation.OnSeekChange;
import com.dev.baqari.binding.viewBinding.annotation.OnTextChange;
import com.dev.baqari.binding.viewBinding.annotation.OnTouch;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


class FieldTypeApply {
    @SuppressLint("StaticFieldLeak")
    static Context context;

    static void apply(Object object, Field field, Preference preference) {
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

    static void apply(Object object, Field field, File preference) {
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
