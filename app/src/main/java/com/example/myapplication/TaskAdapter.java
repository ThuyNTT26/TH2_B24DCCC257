package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private Context context;
    private List<TaskModel> taskList;

    public TaskAdapter(Context context, List<TaskModel> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task_card, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TaskModel task = taskList.get(position);

        holder.tvTitle.setText(task.getTitle());
        holder.tvTime.setText(task.getTime());

        if (task.getNote() != null && !task.getNote().trim().isEmpty()) {
            holder.layoutNote.setVisibility(View.VISIBLE);
            holder.tvNote.setText(task.getNote());
        } else {
            holder.layoutNote.setVisibility(View.GONE);
        }

        // Reset listener before setting checked state to avoid unwanted triggers during scroll/rebind
        holder.cbTask.setOnCheckedChangeListener(null);
        holder.cbTask.setChecked(task.isCompleted());

        holder.cbTask.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                task.setCompleted(true);
                int currentPos = holder.getAdapterPosition();
                if (currentPos != RecyclerView.NO_POSITION) {
                    Toast.makeText(context, "Đã hoàn thành: " + task.getTitle(), Toast.LENGTH_SHORT).show();
                    taskList.remove(currentPos);
                    notifyItemRemoved(currentPos);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void addTask(TaskModel task) {
        taskList.add(0, task);
        notifyItemInserted(0);
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        CheckBox cbTask;
        TextView tvTitle, tvTime, tvNote;
        LinearLayout layoutNote;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            cbTask = itemView.findViewById(R.id.cbTask);
            tvTitle = itemView.findViewById(R.id.tvTaskTitle);
            tvTime = itemView.findViewById(R.id.tvTaskTime);
            tvNote = itemView.findViewById(R.id.tvTaskNote);
            layoutNote = itemView.findViewById(R.id.layoutNote);
        }
    }
}