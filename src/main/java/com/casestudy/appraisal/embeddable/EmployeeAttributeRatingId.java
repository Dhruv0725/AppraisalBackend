package com.casestudy.appraisal.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class EmployeeAttributeRatingId implements Serializable {


    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "attribute_id")
    private int attributeId;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    @Override
    public String toString() {
        return "EmployeeAttributeRatingId{" +
                "employeeId=" + employeeId +
                ", attributeId=" + attributeId +
                '}';
    }

    public EmployeeAttributeRatingId(){}

    public EmployeeAttributeRatingId(int employeeId, int attributeId) {
        this.employeeId = employeeId;
        this.attributeId = attributeId;
    }
}
