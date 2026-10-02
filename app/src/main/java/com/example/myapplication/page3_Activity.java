package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

public class page3_Activity extends AppCompatActivity {

    private EditText edtTaskTitle;
    private EditText edtTaskTime;
    private EditText edtTaskNote;

    private Calendar selectedCalendar;
    private SimpleDateFormat dateTimeFormatter;
    private boolean isDateTimeSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        edtTaskTitle = findViewById(R.id.edtTaskTitle);
        edtTaskTime = findViewById(R.id.edtTaskTime);
        edtTaskNote = findViewById(R.id.edtTaskNote);
        Button btnSaveTask = findViewById(R.id.btnSaveTask);

        selectedCalendar = Calendar.getInstance();
        // Standard international format: "yyyy-MM-dd HH:mm" (ISO 8601 standard format)
        dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

        // Click to choose Date & Time via standard DatePickerDialog and TimePickerDialog
        edtTaskTime.setOnClickListener(v -> showDateTimePicker());

        btnSaveTask.setOnClickListener(v -> {
            String title = edtTaskTitle.getText().toString().trim();
            String time = edtTaskTime.getText().toString().trim();
            String note = edtTaskNote.getText().toString().trim();

            if (title.isEmpty()) {
                Toast.makeText(page3_Activity.this, "Vui lòng nhập tên công việc!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (time.isEmpty() || !isDateTimeSelected) {
                Toast.makeText(page3_Activity.this, "Vui lòng chọn thời gian hoàn thành!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Automatically check if the selected date is today or upcoming
            Calendar now = Calendar.getInstance();
            boolean isToday = (now.get(Calendar.YEAR) == selectedCalendar.get(Calendar.YEAR)) &&
                    (now.get(Calendar.DAY_OF_YEAR) == selectedCalendar.get(Calendar.DAY_OF_YEAR));

            TaskModel newTask = new TaskModel(UUID.randomUUID().toString(), title, time, note, isToday);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("NEW_TASK", newTask);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    private void showDateTimePicker() {
        int currentYear = selectedCalendar.get(Calendar.YEAR);
        int currentMonth = selectedCalendar.get(Calendar.MONTH);
        int currentDay = selectedCalendar.get(Calendar.DAY_OF_MONTH);

        // 1. Show DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                page3_Activity.this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    selectedCalendar.set(Calendar.YEAR, year);
                    selectedCalendar.set(Calendar.MONTH, monthOfYear);
                    selectedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    // 2. Show TimePickerDialog after date is selected
                    int currentHour = selectedCalendar.get(Calendar.HOUR_OF_DAY);
                    int currentMinute = selectedCalendar.get(Calendar.MINUTE);

                    TimePickerDialog timePickerDialog = new TimePickerDialog(
                            page3_Activity.this,
                            (timeView, hourOfDay, minute) -> {
                                selectedCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                selectedCalendar.set(Calendar.MINUTE, minute);

                                // Format in international standard (yyyy-MM-dd HH:mm)
                                String formattedDateTime = dateTimeFormatter.format(selectedCalendar.getTime());
                                edtTaskTime.setText(formattedDateTime);
                                isDateTimeSelected = true;
                            },
                            currentHour,
                            currentMinute,
                            true // 24-hour format
                    );

                    timePickerDialog.show();
                },
                currentYear,
                currentMonth,
                currentDay
        );

        datePickerDialog.show();
    }
}