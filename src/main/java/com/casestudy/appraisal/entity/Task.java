package com.casestudy.appraisal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="task_desc")
    private String taskDescription;

    @Column(name="start_date")
    private Date startTime;

    @Column(name="end_date")
    private Date endTime;

    @Column(name="appraise")
    private boolean appraise;


    @Column(name="task_title")
    private String taskTitle;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="employee_id")
    private Employee employeeId;

    public Task(){

    }

    public Task(String taskDescription, Date startTime, Date endTime, boolean appraise, String taskTitle, Employee employeeId) {
        this.taskDescription = taskDescription;
        this.startTime = startTime;
        this.endTime = endTime;
        this.appraise = appraise;
        this.taskTitle = taskTitle;
        this.employeeId = employeeId;
    }

    public Task(String taskDescription, Date startTime, Date endTime, boolean appraise) {
        this.taskDescription = taskDescription;
        this.startTime = startTime;
        this.endTime = endTime;
        this.appraise = appraise;
    }

    public Task(String taskDescription, Date startTime, Date endTime, boolean appraise, Employee employeeId) {
        this.taskDescription = taskDescription;
        this.startTime = startTime;
        this.endTime = endTime;
        this.appraise = appraise;
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }



    public boolean isAppraise() {
        return appraise;
    }

    public void setAppraise(boolean appraise) {
        this.appraise = appraise;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskDescription='" + taskDescription + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", appraise=" + appraise +
                ", taskTitle='" + taskTitle + '\'' +
                '}';
    }
}


