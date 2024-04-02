package com.casestudy.appraisal.dao;

import com.casestudy.appraisal.entity.Task;

import java.util.List;

public interface TaskDAO {

    List<Task> getAllTasks();

    Task getTask(int id);

    void addTask(Task theTask);

    void removeTask(int id);

    List<Task> getTaskByEmployeeId(int id);

    Task addTaskForEmployee(Task theTask ,int id);

    List<Task> getAppraisalTasks(int empId);

    boolean makeTaskAppraisable(List<Integer> taskIds);

}
