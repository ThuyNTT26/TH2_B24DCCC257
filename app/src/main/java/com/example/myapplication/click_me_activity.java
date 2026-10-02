package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class click_me_activity extends Activity {
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.intents);
        Button b_click = findViewById(R.id.bt_click_me);
        b_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent(click_me_activity.this , MainActivity.class);
                startActivity(i);
                                      }
                                  }
        );
    }
}
