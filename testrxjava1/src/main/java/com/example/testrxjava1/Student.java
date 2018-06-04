package com.example.testrxjava1;

import java.util.List;

/**
 * Created by liu on 18-6-4.
 */

public class Student {
    private String name;
    private List<Course> course;

    public Student(String name, List<Course> course) {
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }
}
