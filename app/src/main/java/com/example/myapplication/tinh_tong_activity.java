package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class tinh_tong_activity extends Activity {
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.main_layout);
        EditText t_a = findViewById(R.id.texta);
        EditText t_b = findViewById(R.id.textb);
        Button b = findViewById(R.id.bt_tinh_tong);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(t_a.getText()+"");
                int b = Integer.parseInt(t_b.getText()+"");
                int tong=a+b;
                Toast.makeText(tinh_tong_activity.this,tong+"", Toast.LENGTH_LONG).show();
            }
        });
    }
}
