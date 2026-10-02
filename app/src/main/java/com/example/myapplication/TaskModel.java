package com.example.myapplication;

import java.io.Serializable;

public class TaskModel implements Serializable {
    private String id;
    private String title;
    private String time;
    private String note;
    private boolean isToday;
    private boolean isCompleted;

    public TaskModel(String id, String title, String time, String note, boolean isToday) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.note = note;
        this.isToday = isToday;
        this.isCompleted = false;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getNote() {
        return note;
    }

    public boolean isToday() {
        return isToday;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}