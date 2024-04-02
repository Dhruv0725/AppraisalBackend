package com.casestudy.appraisal.dao;

import com.casestudy.appraisal.entity.Designations;

import java.util.List;

public interface DesignationDAO {
    List<Designations> getDesignations();
    Designations getDesignations(int id);
}
