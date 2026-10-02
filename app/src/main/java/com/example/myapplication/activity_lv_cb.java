package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class activity_lv_cb extends Activity {
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.listview_cb);
        ListView lv = findViewById(R.id.lv_cb);
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Hà Nội"); arr.add("Tuyên Quang");
        arr.add("Ninh Bình"); arr.add("Hưng Yên");
        ArrayAdapter<String> adap= new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, arr);
        lv.setAdapter(adap);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                AlertDialog.Builder ad = new AlertDialog.Builder(
//                        activity_lv_cb.this
//                );
//                ad.setTitle("Hiển thị item listview");
//                ad.setMessage(arr.get(position));
//                ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                });
//                ad.show();

                // code mới
                arr.add("Nghe An");
                adap.notifyDataSetChanged(); // có dữ liệu thay đổi thì cập nhật
            }
        });
    }
}
