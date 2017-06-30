package com.dev.baqari.inject;

import android.content.Intent;

import com.dev.baqari.binding.contextBinding.ActivityViewModel;
import com.dev.baqari.binding.viewBinding.Binder;
import com.dev.baqari.binding.viewBinding.InjectManager;
import com.dev.baqari.binding.viewBinding.annotation.actions.OnClick;


public class MainViewModel extends ActivityViewModel<MainActivity> {

    private InjectManager manager;

    public void bind(){
        manager = Binder.with(activity).bind(this);
    }

    @OnClick(id = R.id.button)
    public void onClick(){
        Intent intent = new Intent(activity, AnotherActivity.class);
        activity.startActivity(intent);
    }

    public void unBind() {
        manager.reject();
    }
}
