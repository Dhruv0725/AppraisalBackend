package com.casestudy.appraisal.dao;

import com.casestudy.appraisal.entity.Attributes;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AttributeDAOImpl implements AttributeDAO{

    private EntityManager entityManager;

    @Autowired
    public AttributeDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    public Attributes getByAttributeId(int id) {
        return entityManager.find(Attributes.class,id);
    }
}
