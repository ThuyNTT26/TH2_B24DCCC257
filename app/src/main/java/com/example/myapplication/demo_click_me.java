package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class demo_click_me extends Activity {
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.toast);
    }
    public void click_me(View v){
        Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show();
    }
}
