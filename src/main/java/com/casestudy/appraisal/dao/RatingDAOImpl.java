package com.casestudy.appraisal.dao;

import com.casestudy.appraisal.entity.Employee;
import com.casestudy.appraisal.entity.EmployeeAttributeRating;
import com.casestudy.appraisal.entity.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RatingDAOImpl implements RatingDAO{

    private EntityManager entityManager;

    @Autowired
    public RatingDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    @Transactional
    public void create(EmployeeAttributeRating emp) {
        entityManager.persist(emp);
    }

    @Override
    public List<EmployeeAttributeRating> getByEmployeeId(int empId) {
        Employee emp =entityManager.find(Employee.class,empId);
        TypedQuery<EmployeeAttributeRating> query=entityManager.createQuery("from EmployeeAttributeRating where employee=:id",EmployeeAttributeRating.class);
        query.setParameter("id",emp);
        List<EmployeeAttributeRating> tasks=query.getResultList();

        return tasks;
    }
}
