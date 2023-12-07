package com.cs407.morningrecovery;

public class Alarm {
    // Attributes for the alarm
    private int id;
    private int hour;
    private int minute;
    private String amPm;
    private String quizType;

    // Constructor
    public Alarm(int id, int hour, int minute, String amPm, String quizType) {
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.amPm = amPm;
        this.quizType = quizType;
    }

    // Getters and setters for each attribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getAmPm() {
        return amPm;
    }

    public void setAmPm(String amPm) {
        this.amPm = amPm;
    }

    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }
}
