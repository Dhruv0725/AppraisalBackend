package com.casestudy.appraisal.dao;

import com.casestudy.appraisal.entity.EmployeeAttributeRating;

import java.util.List;

public interface RatingDAO {

    void create(EmployeeAttributeRating emp);

    List<EmployeeAttributeRating> getByEmployeeId(int empId);
}
