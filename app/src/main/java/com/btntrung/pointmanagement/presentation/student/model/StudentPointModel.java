package com.btntrung.pointmanagement.presentation.student.model;

import com.btntrung.pointmanagement.entity.Semester;
import com.btntrung.pointmanagement.entity.Subject;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StudentPointModel implements Serializable {
    private int id;
    @SerializedName("attendance_point") private float attendance;
    @SerializedName("test_point") private float test;
    @SerializedName("project_point") private float project;
    @SerializedName("final_point") private float finalPoint;
    @SerializedName("avg") private float avg;
    private Subject subject;
    private Semester semester;

    public StudentPointModel(int id, float attendance, float test, float project, float finalPoint, float avg, Subject subject, Semester semester) {
        this.id = id;
        this.attendance = attendance;
        this.test = test;
        this.project = project;
        this.finalPoint = finalPoint;
        this.avg = avg;
        this.subject = subject;
        this.semester = semester;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAttendance() {
        return attendance;
    }

    public void setAttendance(float attendance) {
        this.attendance = attendance;
    }

    public float getTest() {
        return test;
    }

    public void setTest(float test) {
        this.test = test;
    }

    public float getProject() {
        return project;
    }

    public void setProject(float project) {
        this.project = project;
    }

    public float getFinalPoint() {
        return finalPoint;
    }

    public void setFinalPoint(float finalPoint) {
        this.finalPoint = finalPoint;
    }

    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
}
