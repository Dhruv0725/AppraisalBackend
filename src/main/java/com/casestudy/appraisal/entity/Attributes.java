package com.casestudy.appraisal.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="attributes")
public class Attributes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;


    @Column(name="attribute_name")
    private String attributeName;

    @OneToMany(mappedBy = "attribute")
    private List<EmployeeAttributeRating> ratings;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(name="designation_attribute_mapping",
            joinColumns = @JoinColumn(name="attribute_id"),
            inverseJoinColumns = @JoinColumn(name="designation_id"))
    List<Designations> designations;

    public void addDesignation(Designations des){
        if(designations==null){
            designations=new ArrayList<>();
        }

        designations.add(des);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public List<EmployeeAttributeRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<EmployeeAttributeRating> ratings) {
        this.ratings = ratings;
    }

    public List<Designations> getDesignations() {
        return designations;
    }

    public void setDesignations(List<Designations> designations) {
        this.designations = designations;
    }
}
