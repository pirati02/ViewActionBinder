package com.dev.baqari.binding;

import android.content.Context;
import android.view.View;

import com.dev.baqari.binding.annotation.OnCheckChange;
import com.dev.baqari.binding.annotation.OnClick;
import com.dev.baqari.binding.annotation.OnItemClick;
import com.dev.baqari.binding.annotation.OnItemSelect;
import com.dev.baqari.binding.annotation.OnLongClick;
import com.dev.baqari.binding.annotation.OnScroll;
import com.dev.baqari.binding.annotation.OnSeekChange;
import com.dev.baqari.binding.annotation.OnTextChange;
import com.dev.baqari.binding.annotation.OnTouch;

import java.lang.reflect.Method;

public class Binder {

    private static InjectManager injectManager;

    static {
        injectManager = new InjectManager();
    }

    public static InjectManager bind(final Context context) {
        View view = null;
        for (final Method method : context.getClass().getMethods()) {
            if (method.isAnnotationPresent(OnClick.class)) {
                view = TypeApplier.applyOnClick(method, method.getAnnotation(OnClick.class), context);
            } else if (method.isAnnotationPresent(OnTextChange.class)) {
                view = TypeApplier.applyTextChange(method, method.getAnnotation(OnTextChange.class), context);
            }else if (method.isAnnotationPresent(OnSeekChange.class)) {
                view = TypeApplier.applySeekChange(method, method.getAnnotation(OnSeekChange.class), context);
            }else if (method.isAnnotationPresent(OnItemClick.class)) {
                view = TypeApplier.applyOnItemClick(method, method.getAnnotation(OnItemClick.class), context);
            }else if (method.isAnnotationPresent(OnCheckChange.class)) {
                view = TypeApplier.applyOnCheckChange(method, method.getAnnotation(OnCheckChange.class), context);
            }else if (method.isAnnotationPresent(OnItemSelect.class)) {
                view = TypeApplier.applyOnItemSelect(method, method.getAnnotation(OnItemSelect.class), context);
            }else if (method.isAnnotationPresent(OnTouch.class)) {
                view = TypeApplier.applyOnTouch(method, method.getAnnotation(OnTouch.class), context);
            }else if (method.isAnnotationPresent(OnLongClick.class)) {
                view = TypeApplier.applyOnLongClick(method, method.getAnnotation(OnLongClick.class), context);
            }else if (method.isAnnotationPresent(OnScroll.class)) {
                view = TypeApplier.applyOnScroll(method, method.getAnnotation(OnScroll.class), context);
            }
            if (view != null)
                injectManager.inject(view);
        }
        return injectManager;
    }
}
