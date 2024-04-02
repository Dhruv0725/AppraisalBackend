package com.casestudy.appraisal.dao;

import com.casestudy.appraisal.entity.Designations;
import com.casestudy.appraisal.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DesignationDAOImpl implements DesignationDAO{

    private EntityManager entityManager;

    @Autowired
    public DesignationDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    public List<Designations> getDesignations() {
        TypedQuery<Designations> query=entityManager.createQuery("from Designations",Designations.class);
        List<Designations> designations=query.getResultList();

        return designations;
    }

    public Designations getDesignations(int id){
        return entityManager.find(Designations.class,id);
    }

}
