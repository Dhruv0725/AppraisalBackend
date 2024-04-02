package com.casestudy.appraisal.dao;

import com.casestudy.appraisal.DTO.AppraisalDTO;
import com.casestudy.appraisal.entity.Appraisal;
import com.casestudy.appraisal.entity.Employee;
import com.casestudy.appraisal.entity.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

//admin side...
@Repository
public class AppraisalDAOImp implements AppraisalDAO{

    private EntityManager entityManager;

    @Autowired
    public AppraisalDAOImp(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    public List<Appraisal> getAllAppraisals() {
        TypedQuery<Appraisal> query=entityManager.createQuery("from Appraisal",Appraisal.class);
        List<Appraisal> appraisals=query.getResultList();

        return appraisals;
    }

    @Override
    public Appraisal getAppraisal(int id) {
        return entityManager.find(Appraisal.class,id);
    }


    @Override
    @Transactional
    public boolean addAppraisal(int revId,List<AppraisalDTO> appraisalDTOS) {

        for(AppraisalDTO theAppraisal:appraisalDTOS){
            Appraisal app=new Appraisal();
            app.setId(0);
            Task task=entityManager.find(Task.class,theAppraisal.getTaskId());
            System.out.println(task);
            Employee emp=entityManager.find(Employee.class,revId);
            System.out.println(emp);

            if(task==null || emp ==null || !task.isAppraise()) return false;
            TypedQuery<Appraisal> query = entityManager.createQuery(
                    "SELECT e FROM Appraisal e WHERE e.taskId = :id", Appraisal.class);
            query.setParameter("id", task);

            try{
                query.getSingleResult();
                return false;
            }
            catch (Exception e){

            }
            if(emp.getRoleId()!=1) return false;
            app.setTaskId(task);
            app.setRating(theAppraisal.getRating());
            app.setReviewer(emp);
            entityManager.persist(app);
        }


        return true;
    }

    @Override
    @Transactional
    public void removeAppraisal(int id) {
        Appraisal theTask=entityManager.find(Appraisal.class,id);

        if(theTask!=null){
            entityManager.remove(theTask);
        }
    }

    @Override
    public List<Appraisal> getAppraisedTasks(List<Task> tasks) {
        TypedQuery<Appraisal> query = entityManager.createQuery(
                "SELECT a FROM Appraisal a WHERE a.taskId IN :tasks", Appraisal.class);
        query.setParameter("tasks", tasks);
        List<Appraisal> appraisals = query.getResultList();
        return appraisals;
    }
}
