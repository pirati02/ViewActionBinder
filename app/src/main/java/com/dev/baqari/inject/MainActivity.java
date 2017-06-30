package com.dev.baqari.inject;

import android.os.Bundle;

import com.dev.baqari.binding.contextBinding.ViewModelActivity;

public class MainActivity extends ViewModelActivity<MainViewModel> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel.bind();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.unBind();
    }
}
