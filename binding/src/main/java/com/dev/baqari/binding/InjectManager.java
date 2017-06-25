package com.dev.baqari.binding;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

public class InjectManager {
    private List<View> instances;

    public InjectManager() {
        instances = new ArrayList<>();
    }

    public void inject(View view) {
        instances.add(view);
    }

    public void reject() {
        for (View item : instances) {
            if (item instanceof EditText)
                ((EditText)item).addTextChangedListener(null);
            if (item instanceof Button)
                item.setOnClickListener(null);
            if (item instanceof SeekBar)
                ((SeekBar)item).setOnSeekBarChangeListener(null);
            if (item instanceof ListView)
                ((ListView)item).setOnItemClickListener(null);
        }
    }
}
