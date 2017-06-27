package com.dev.baqari.binding.contextBinding;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

@SuppressLint("Registered")
public class ViewModelActivity<T extends ActivityViewModel> extends Activity {

    public T viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            instance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    void instance() throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        viewModel = clazz.newInstance();
        Field activityField = viewModel.getClass().getField("activity");
        activityField.set(viewModel, this);
    }
}
