package com.dev.baqari.inject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dev.baqari.binding.viewBinding.Binder;
import com.dev.baqari.binding.viewBinding.InjectManager;
import com.dev.baqari.binding.viewBinding.annotation.actions.OnClick;
import com.dev.baqari.binding.viewBinding.annotation.component.Receiver;
import com.dev.baqari.binding.viewBinding.annotation.storage.File;
import com.dev.baqari.binding.viewBinding.annotation.storage.Preference;
import com.dev.baqari.binding.viewBinding.annotation.storage.Storage;

public class AnotherActivity extends AppCompatActivity {

    @Preference(forName = "default")
    SharedPreferences preferences;

    @File(fileName = "lazy.pdf", storage = Storage.INTERNAL)
    java.io.File cv;

    InjectManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        manager = Binder.instance().bind(this);
    }

    @OnClick(id = R.id.button2)
    public void onClick(){
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
    }

    @Receiver(actions = {"android.net.wifi.WIFI_STATE_CHANGED","android.net.wifi.STATE_CHANGE"})
    public void received(Intent data){
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.reject();
    }
}
