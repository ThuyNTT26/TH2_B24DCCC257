package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class page2_Activity extends AppCompatActivity {

    private RecyclerView rvTodayTasks;
    private RecyclerView rvUpcomingTasks;

    private TaskAdapter todayAdapter;
    private TaskAdapter upcomingAdapter;

    private List<TaskModel> todayTaskList;
    private List<TaskModel> upcomingTaskList;

    private ActivityResultLauncher<Intent> addTaskLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        TextView tvUsernameHeader = findViewById(R.id.tvUsernameHeader);
        ImageButton btnAddTask = findViewById(R.id.btnAddTask);
        rvTodayTasks = findViewById(R.id.rvTodayTasks);
        rvUpcomingTasks = findViewById(R.id.rvUpcomingTasks);

        // Display username passed from Login
        String username = getIntent().getStringExtra("USERNAME");
        if (username != null && !username.trim().isEmpty()) {
            tvUsernameHeader.setText(username);
        } else {
            tvUsernameHeader.setText("ngthuy");
        }

        // Initialize Task Lists with sample data
        initSampleData();

        // Setup RecyclerViews
        todayAdapter = new TaskAdapter(this, todayTaskList);
        rvTodayTasks.setLayoutManager(new LinearLayoutManager(this));
        rvTodayTasks.setAdapter(todayAdapter);

        upcomingAdapter = new TaskAdapter(this, upcomingTaskList);
        rvUpcomingTasks.setLayoutManager(new LinearLayoutManager(this));
        rvUpcomingTasks.setAdapter(upcomingAdapter);

        addTaskLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        TaskModel newTask = result.getData().getSerializableExtra("NEW_TASK", TaskModel.class);

                        if (newTask != null) {
                            if (newTask.isToday()) {
                                todayAdapter.addTask(newTask);
                                rvTodayTasks.scrollToPosition(0);
                            } else {
                                upcomingAdapter.addTask(newTask);
                                rvUpcomingTasks.scrollToPosition(0);
                            }
                            Toast.makeText(page2_Activity.this, "Đã thêm công việc thành công!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        // Click '+' button to open Screen 3
        btnAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(page2_Activity.this, page3_Activity.class);
            addTaskLauncher.launch(intent);
        });
    }

    private void initSampleData() {
        todayTaskList = new ArrayList<>();
        todayTaskList.add(new TaskModel(
                UUID.randomUUID().toString(),
                "Họp nhóm đồ án Todo App",
                "10:00 - Hôm nay",
                "Thảo luận cấu trúc 3 màn hình và giao diện",
                true
        ));
        todayTaskList.add(new TaskModel(
                UUID.randomUUID().toString(),
                "Hoàn thiện UI và kiểm thử",
                "14:30 - Hôm nay",
                "Kiểm tra chức năng ẩn task khi tick checkbox",
                true
        ));

        upcomingTaskList = new ArrayList<>();
        upcomingTaskList.add(new TaskModel(
                UUID.randomUUID().toString(),
                "Nộp báo cáo cuối kỳ",
                "09:00 - 25/10/2026",
                "Tổng hợp tài liệu và kiểm tra mã nguồn",
                false
        ));
        upcomingTaskList.add(new TaskModel(
                UUID.randomUUID().toString(),
                "Mua dụng cụ học tập",
                "18:00 - Ngày mai",
                "Vở ghi, sổ tay và bút dạ",
                false
        ));
    }
}