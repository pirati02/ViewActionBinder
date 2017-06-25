package com.dev.baqari.binding;

import android.content.Context;
import android.view.View;

import com.dev.baqari.binding.annotation.OnClick;
import com.dev.baqari.binding.annotation.OnItemClick;
import com.dev.baqari.binding.annotation.OnSeekChange;
import com.dev.baqari.binding.annotation.OnTextChange;

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
                view = TypeInject.applyType(method, method.getAnnotation(OnClick.class), context);
            } else if (method.isAnnotationPresent(OnTextChange.class)) {
                view = TypeInject.applyType(method, method.getAnnotation(OnTextChange.class), context);
            }else if (method.isAnnotationPresent(OnSeekChange.class)) {
                view = TypeInject.applyType(method, method.getAnnotation(OnSeekChange.class), context);
            }else if (method.isAnnotationPresent(OnItemClick.class)) {
                view = TypeInject.applyType(method, method.getAnnotation(OnItemClick.class), context);
            }
            if (view != null)
                injectManager.inject(view);
        }
        return injectManager;
    }
}
