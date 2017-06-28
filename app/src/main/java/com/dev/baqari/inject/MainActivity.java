package com.dev.baqari.inject;

import android.os.Bundle;
import android.widget.Toast;

import com.dev.baqari.binding.contextBinding.ViewModelActivity;
import com.dev.baqari.binding.viewBinding.Binder;
import com.dev.baqari.binding.viewBinding.annotation.OnClick;

public class MainActivity extends ViewModelActivity<MainViewModel> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel.bind();
    }
}
