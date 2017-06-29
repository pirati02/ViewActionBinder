package com.dev.baqari.inject;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.dev.baqari.binding.storageBinding.File;
import com.dev.baqari.binding.storageBinding.Preference;
import com.dev.baqari.binding.storageBinding.Storage;
import com.dev.baqari.binding.viewBinding.Binder;
import com.dev.baqari.binding.viewBinding.annotation.OnClick;

public class AnotherActivity extends AppCompatActivity {

    @Preference(forName = "default")
    SharedPreferences preferences;

    @File(fileName = "lazy.pdf", storage = Storage.INTERNAL)
    java.io.File cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        Binder.without().bind(this);
    }

    @OnClick(id = R.id.button2)
    public void onClick(){
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
    }
}
