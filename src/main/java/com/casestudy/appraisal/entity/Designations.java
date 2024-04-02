package com.casestudy.appraisal.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="designations")
public class Designations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;



    @Column(name="designation_name")
    private String designationName;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY)
            @JoinTable(name="designation_attribute_mapping",
                    joinColumns = @JoinColumn(name="designation_id"),
                    inverseJoinColumns = @JoinColumn(name="attribute_id"))
    List<Attributes> attributes;

    public List<Attributes> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attributes> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(Attributes att){
        if(attributes==null){
            attributes=new ArrayList<>();
        }

        attributes.add(att);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    @Override
    public String toString() {
        return "Designations{" +
                "id=" + id +
                ", designationName='" + designationName + '\'' +
                '}';
    }

}
