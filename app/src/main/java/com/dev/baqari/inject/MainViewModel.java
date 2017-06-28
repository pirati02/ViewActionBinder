package com.dev.baqari.inject;

import android.content.Intent;
import android.widget.Toast;

import com.dev.baqari.binding.contextBinding.ActivityViewModel;
import com.dev.baqari.binding.viewBinding.Binder;
import com.dev.baqari.binding.viewBinding.annotation.OnClick;


public class MainViewModel extends ActivityViewModel<MainActivity> {

    public void bind(){
        Binder.with(activity).bind(this);
    }

    @OnClick(id = R.id.button)
    public void onClick(){
        Intent intent = new Intent(activity, AnotherActivity.class);
        activity.startActivity(intent);
    }
}