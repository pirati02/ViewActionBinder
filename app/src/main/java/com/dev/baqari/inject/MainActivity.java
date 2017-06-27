package com.dev.baqari.inject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.dev.baqari.binding.Binder;
import com.dev.baqari.binding.InjectManager;
import com.dev.baqari.binding.annotation.OnCheckChange;
import com.dev.baqari.binding.annotation.OnClick;
import com.dev.baqari.binding.annotation.OnItemClick;
import com.dev.baqari.binding.annotation.OnScroll;
import com.dev.baqari.binding.annotation.OnSeekChange;
import com.dev.baqari.binding.annotation.OnTextChange;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    InjectManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = Binder.bind(this);
    }

    @OnClick(id = R.id.button)
    public void onClick() {
        manager.reject();
    }

    @OnTextChange(id = R.id.edit_text)
    public void textChange(String text) {
    }

    @OnSeekChange(id = R.id.seek_bar)
    public void seekChange(int progress) {
    }

    @OnCheckChange(id = R.id.check_box)
    public void checkChange(boolean cheched) {
        Toast.makeText(this, String.valueOf(cheched), Toast.LENGTH_SHORT).show();
    }

    @OnCheckChange(id = R.id.radio_button)
    public void chechChang () {
    }

    @OnScroll(id = R.id.layout)
    public void onScroll(MotionEvent m1, MotionEvent m2, float x, float y){
        Toast.makeText(this, "scrolled", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.reject();
    }
}
