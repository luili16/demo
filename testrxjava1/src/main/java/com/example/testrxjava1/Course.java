package com.example.testrxjava1;

/**
 * Created by liu on 18-6-4.
 */

public class Course {

    private String teacher;

    public Course(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "teacher='" + teacher + '\'' +
                '}';
    }
}
