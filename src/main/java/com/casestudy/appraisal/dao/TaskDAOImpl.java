package com.casestudy.appraisal.dao;


import com.casestudy.appraisal.entity.Employee;
import com.casestudy.appraisal.entity.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TaskDAOImpl implements TaskDAO {

    private EntityManager entityManager;

    @Autowired
    public TaskDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    public List<Task> getAllTasks() {
        TypedQuery<Task> query=entityManager.createQuery("from Task",Task.class);
        List<Task> tasks=query.getResultList();

        return tasks;
    }

    @Override
    public Task getTask(int id) {
       return entityManager.find(Task.class,id);
    }

    @Override
    @Transactional
    public void addTask(Task theTask) {

        entityManager.persist(theTask);
    }

    @Override
    @Transactional
    public void removeTask(int id) {
        Task theTask=entityManager.find(Task.class,id);

        if(theTask!=null){
            entityManager.remove(theTask);
        }
    }

    @Override
    public List<Task> getTaskByEmployeeId(int id) {
        Employee emp =entityManager.find(Employee.class,id);
        TypedQuery<Task> query=entityManager.createQuery("from Task where employeeId=:id",Task.class);
        query.setParameter("id",emp);
        List<Task> tasks=query.getResultList();


        return tasks;
    }

    @Override
    @Transactional
    public Task addTaskForEmployee(Task theTask, int id) {
        Employee emp=entityManager.find(Employee.class,id);
        theTask.setEmployeeId(emp);
        emp.getTasks().add(theTask);

        entityManager.persist(emp);

        return theTask;
    }

    //For Admin
    @Override
    public List<Task> getAppraisalTasks(int id) {
        Employee emp =entityManager.find(Employee.class,id);
        if(emp==null) return new ArrayList<>();
        return emp.getTasks().stream().filter(Task::isAppraise).collect(Collectors.toList());
    }
    @Override
    @Transactional
    public boolean makeTaskAppraisable(List<Integer> taskIds){
        for(int x:taskIds){
            Task tsk=entityManager.find(Task.class,x);
            System.out.println(tsk.toString());
            if(tsk==null || tsk.isAppraise()) return false;
        }

        for(int x:taskIds){
            Task tsk=entityManager.find(Task.class,x);
            tsk.setAppraise(true);
            entityManager.merge(tsk);
        }

        return true;
    }



}
