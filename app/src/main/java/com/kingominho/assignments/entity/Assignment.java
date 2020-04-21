package com.kingominho.assignments.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assignment_table")
public class Assignment {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "topic")
    private String topic;

    @ColumnInfo(name = "subject")
    private int subject;

    @ColumnInfo(name = "due_date")
    private String dueDate;

    @ColumnInfo(name = "is_completed")
    private int isCompleted;

    @ColumnInfo(name = "color_code")
    private int colorCode;

    public Assignment(String topic, int subject, String dueDate, int isCompleted, int colorCode) {
        this.topic = topic;
        this.subject = subject;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
        this.colorCode = colorCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(int isCompleted) {
        this.isCompleted = isCompleted;
    }

    public int getColorCode() {
        return colorCode;
    }

    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }
}
