package com.dev.baqari.binding.viewBinding;

import android.content.Context;
import android.view.View;

import com.dev.baqari.binding.contextBinding.ActivityViewModel;
import com.dev.baqari.binding.viewBinding.annotation.storage.File;
import com.dev.baqari.binding.viewBinding.annotation.storage.Preference;
import com.dev.baqari.binding.viewBinding.annotation.actions.OnCheckChange;
import com.dev.baqari.binding.viewBinding.annotation.actions.OnClick;
import com.dev.baqari.binding.viewBinding.annotation.actions.OnItemClick;
import com.dev.baqari.binding.viewBinding.annotation.actions.OnItemSelect;
import com.dev.baqari.binding.viewBinding.annotation.actions.OnLongClick;
import com.dev.baqari.binding.viewBinding.annotation.actions.OnSeekChange;
import com.dev.baqari.binding.viewBinding.annotation.actions.OnTextChange;
import com.dev.baqari.binding.viewBinding.annotation.actions.OnTouch;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Binder {

    private InjectManager injectManager = new InjectManager();

    public static Binder instance(Context context) {
        MethodTypeApply.context = context;
        FieldTypeApply.context = context;
        return new Binder();
    }

    public static Binder instance() {
        return new Binder();
    }

    public InjectManager bind(ActivityViewModel object) {
        if (!injectManager.isApplied()) {
            if (MethodTypeApply.context == null)
                try {
                    throw new Exception("You forgot to invoke the method instance(Context context)");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            else {
                binding(object);
            }
        }
        return injectManager;
    }

    public InjectManager bind(Context object) {
        if (!injectManager.isApplied()) {
            MethodTypeApply.context = object;
            FieldTypeApply.context = object;
            binding(object);
        }
        return injectManager;
    }

    public void binding(Object object){
        View view = null;
        for (final Method method : object.getClass().getMethods()) {
            if (method.isAnnotationPresent(OnClick.class)) {
                view = MethodTypeApply.applyOnClick(method, method.getAnnotation(OnClick.class), object);
            }
            if (method.isAnnotationPresent(OnTextChange.class)) {
                view = MethodTypeApply.applyTextChange(method, method.getAnnotation(OnTextChange.class), object);
            }
            if (method.isAnnotationPresent(OnSeekChange.class)) {
                view = MethodTypeApply.applySeekChange(method, method.getAnnotation(OnSeekChange.class), object);
            }
            if (method.isAnnotationPresent(OnItemClick.class)) {
                view = MethodTypeApply.applyOnItemClick(method, method.getAnnotation(OnItemClick.class), object);
            }
            if (method.isAnnotationPresent(OnCheckChange.class)) {
                view = MethodTypeApply.applyOnCheckChange(method, method.getAnnotation(OnCheckChange.class), object);
            }
            if (method.isAnnotationPresent(OnItemSelect.class)) {
                view = MethodTypeApply.applyOnItemSelect(method, method.getAnnotation(OnItemSelect.class), object);
            }
            if (method.isAnnotationPresent(OnTouch.class)) {
                view = MethodTypeApply.applyOnTouch(method, method.getAnnotation(OnTouch.class), object);
            }
            if (method.isAnnotationPresent(OnLongClick.class)) {
                view = MethodTypeApply.applyOnLongClick(method, method.getAnnotation(OnLongClick.class), object);
            }
            if (view != null)
                injectManager.inject(view);
        }
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Preference.class)) {
                FieldTypeApply.apply(object, field, field.getAnnotation(Preference.class));
            }
            if (field.isAnnotationPresent(File.class)) {
                FieldTypeApply.apply(object, field, field.getAnnotation(File.class));
            }
        }
        injectManager.setApplied(true);
    }
}
