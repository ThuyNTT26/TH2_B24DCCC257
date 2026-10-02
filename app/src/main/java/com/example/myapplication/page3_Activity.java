package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class page3_Activity extends AppCompatActivity {

    private EditText edtTaskTitle;
    private EditText edtTaskTime;
    private EditText edtTaskNote;
    private RadioButton rbToday;
    private Button btnSaveTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        edtTaskTitle = findViewById(R.id.edtTaskTitle);
        edtTaskTime = findViewById(R.id.edtTaskTime);
        edtTaskNote = findViewById(R.id.edtTaskNote);
        rbToday = findViewById(R.id.rbToday);
        btnSaveTask = findViewById(R.id.btnSaveTask);

        btnSaveTask.setOnClickListener(v -> {
            String title = edtTaskTitle.getText().toString().trim();
            String time = edtTaskTime.getText().toString().trim();
            String note = edtTaskNote.getText().toString().trim();
            boolean isToday = rbToday.isChecked();

            if (title.isEmpty()) {
                Toast.makeText(page3_Activity.this, "Vui lòng nhập tên công việc!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (time.isEmpty()) {
                time = isToday ? "Hôm nay" : "Sắp tới";
            }

            TaskModel newTask = new TaskModel(UUID.randomUUID().toString(), title, time, note, isToday);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("NEW_TASK", newTask);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}