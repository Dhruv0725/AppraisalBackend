package com.casestudy.appraisal.entity;

import jakarta.persistence.*;

@Entity
@Table(name="appraisal")
public class Appraisal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="task_id")
    private Task taskId;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="reviewer")
    private Employee reviewer;

    @Column(name="rating")
    private int rating;


    public Employee getReviewer() {
        return reviewer;
    }

    public void setReviewer(Employee reviewer) {
        this.reviewer = reviewer;
    }



    public Appraisal(){

    }
    public Appraisal(Task taskId, Employee appraiser, int rating) {
        this.taskId = taskId;
        this.reviewer = appraiser;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Task getTaskId() {
        return taskId;
    }

    public void setTaskId(Task taskId) {
        this.taskId = taskId;
    }


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Appraisal{" +
                "id=" + id +
                ", rating=" + rating +

                '}';
    }
}
