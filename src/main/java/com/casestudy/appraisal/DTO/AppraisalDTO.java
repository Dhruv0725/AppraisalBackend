package com.casestudy.appraisal.DTO;

public class AppraisalDTO {

    private int taskId;

    private int rating;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
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
        return "AppraisalDTO{" +
                "taskId=" + taskId +
                ", rating=" + rating +
                '}';
    }
}
