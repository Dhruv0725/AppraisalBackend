package com.casestudy.appraisal.entity;

import com.casestudy.appraisal.embeddable.EmployeeAttributeRatingId;
import jakarta.persistence.*;


@Entity
@Table(name = "employee_attribute_rating")
public class EmployeeAttributeRating {

    @Override
    public String toString() {
        return "EmployeeAttributeRating{" +
                "id=" + id +

                ", rating=" + rating +
                '}';
    }

    @EmbeddedId
    private EmployeeAttributeRatingId id;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @MapsId("attributeId")
    @JoinColumn(name = "attribute_id")
    private Attributes attribute;

    @Column(name = "rating")
    private int rating;

    public EmployeeAttributeRatingId getId() {
        return id;
    }

    public void setId(EmployeeAttributeRatingId id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Attributes getAttribute() {
        return attribute;
    }

    public void setAttribute(Attributes attribute) {
        this.attribute = attribute;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}