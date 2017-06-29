package com.dev.baqari.binding.viewBinding;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

public class InjectManager {
    private List<View> instances;
    private boolean mIsApplied;

    InjectManager() {
        instances = new ArrayList<>();
    }

    void inject(View view) {
        mIsApplied = true;
        instances.add(view);
    }

    public void reject() {
        mIsApplied = false;
        MethodTypeApply.context = null;
        FieldTypeApply.context = null;

        for (View item : instances) {
            if (item instanceof EditText)
                ((EditText)item).addTextChangedListener(null);
            if (item instanceof SeekBar)
                ((SeekBar)item).setOnSeekBarChangeListener(null);
            if (item instanceof AbsListView)
                ((AbsListView)item).setOnItemClickListener(null);
            if (item instanceof CompoundButton)
                ((CompoundButton)item).setOnCheckedChangeListener(null);
            if (item instanceof AbsListView)
                ((AbsListView)item).setOnItemSelectedListener(null);
            if (item instanceof CompoundButton)
                ((CompoundButton)item).setOnCheckedChangeListener(null);

            item.setOnLongClickListener(null);
            item.setOnClickListener(null);
            item.setOnTouchListener(null);
        }
    }

    public boolean isApplied(){
        return mIsApplied;
    }

    public void setApplied(boolean applied) {
        this.mIsApplied = applied;
    }
}
