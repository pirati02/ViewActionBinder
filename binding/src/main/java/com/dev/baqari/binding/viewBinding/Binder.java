package com.dev.baqari.binding.viewBinding;

import android.content.Context;
import android.view.View;

import com.dev.baqari.binding.viewBinding.annotation.OnCheckChange;
import com.dev.baqari.binding.viewBinding.annotation.OnClick;
import com.dev.baqari.binding.viewBinding.annotation.OnItemClick;
import com.dev.baqari.binding.viewBinding.annotation.OnItemSelect;
import com.dev.baqari.binding.viewBinding.annotation.OnLongClick;
import com.dev.baqari.binding.viewBinding.annotation.OnSeekChange;
import com.dev.baqari.binding.viewBinding.annotation.OnTextChange;
import com.dev.baqari.binding.viewBinding.annotation.OnTouch;

import java.lang.reflect.Method;

public class Binder {

    private static InjectManager injectManager;
    private static Binder binder;
    static {
        injectManager = new InjectManager();
        binder = new Binder();
    }

    public static Binder with(Context context){
        TypeApplier.context = context;
        return binder;
    }

    public static void bind(Object object) {
        View view = null;
        for (final Method method : object.getClass().getMethods()) {
            if (method.isAnnotationPresent(OnClick.class)) {
                view = TypeApplier.applyOnClick(method, method.getAnnotation(OnClick.class), object);
            } else if (method.isAnnotationPresent(OnTextChange.class)) {
                view = TypeApplier.applyTextChange(method, method.getAnnotation(OnTextChange.class), object);
            }else if (method.isAnnotationPresent(OnSeekChange.class)) {
                view = TypeApplier.applySeekChange(method, method.getAnnotation(OnSeekChange.class), object);
            }else if (method.isAnnotationPresent(OnItemClick.class)) {
                view = TypeApplier.applyOnItemClick(method, method.getAnnotation(OnItemClick.class), object);
            }else if (method.isAnnotationPresent(OnCheckChange.class)) {
                view = TypeApplier.applyOnCheckChange(method, method.getAnnotation(OnCheckChange.class), object);
            }else if (method.isAnnotationPresent(OnItemSelect.class)) {
                view = TypeApplier.applyOnItemSelect(method, method.getAnnotation(OnItemSelect.class), object);
            }else if (method.isAnnotationPresent(OnTouch.class)) {
                view = TypeApplier.applyOnTouch(method, method.getAnnotation(OnTouch.class), object);
            }else if (method.isAnnotationPresent(OnLongClick.class)) {
                view = TypeApplier.applyOnLongClick(method, method.getAnnotation(OnLongClick.class), object);
            }
            if (view != null)
                injectManager.inject(view);
        }
    }
}
