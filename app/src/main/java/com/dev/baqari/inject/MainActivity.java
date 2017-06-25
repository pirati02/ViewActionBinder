package com.dev.baqari.inject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dev.baqari.binding.Binder;
import com.dev.baqari.binding.InjectManager;
import com.dev.baqari.binding.annotation.OnClick;
import com.dev.baqari.binding.annotation.OnItemClick;
import com.dev.baqari.binding.annotation.OnSeekChange;
import com.dev.baqari.binding.annotation.OnTextChange;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> bla = new ArrayList<>();
    InjectManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = Binder.bind(this);
        bla.add("asdfa");
        bla.add("fbsdfgs");
        bla.add("sdfgsdfgds");
        bla.add("sdfgsdfg");
        bla.add("sdfgsdf");
        bla.add("sdfgsdf");
        bla.add("sdfgsdf");
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, bla);
        ((ListView)findViewById(R.id.list_view)).setAdapter(adapter);

    }

    @OnClick(id = R.id.button)
    public void onClick(){
        Toast.makeText(this, "Hello World", Toast.LENGTH_LONG).show();
    }

    @OnTextChange(id = R.id.edit_text)
    public void textChange(String text){
        Log.d("OnTextChange", "textChange: " + text);
    }

    @OnSeekChange(id = R.id.seek_bar)
    public void seekChange(int progress){
        Log.d("OnSeekChange", "OnSeekChange: " + String.valueOf(progress));
    }

    @OnItemClick(id = R.id.list_view)
    public void onItemClick(Object item, int position, View view){
        int ad = 2;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.reject();
    }
}
