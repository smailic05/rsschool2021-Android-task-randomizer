package com.rsschool.android2021;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FirstFragment.Generated,SecondFragment.BackToFirst {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    @Override
    public void onBackPressed() {

        SecondFragment secondFragment= (SecondFragment) getSupportFragmentManager().findFragmentByTag("SECOND_FRAGMENT");
        if (secondFragment!=null && secondFragment.isVisible())
        {
            TextView previous=findViewById(R.id.result);
            int var=Integer.parseInt(previous.getText().toString());
            openFirstFragment(var);
        }
        else
            super.onBackPressed();
    }

    private void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        transaction.commit();
    }

    private void openSecondFragment(int min, int max) {
        final Fragment secondFragment = SecondFragment.newInstance(min,max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment,"SECOND_FRAGMENT");
        transaction.commit();
    }

    @Override
    public void toGenerate(int min, int max) {
        openSecondFragment(min,max);
    }

    @Override
    public void backToFirstFragment(int previous) {
        openFirstFragment(previous);
    }
}
