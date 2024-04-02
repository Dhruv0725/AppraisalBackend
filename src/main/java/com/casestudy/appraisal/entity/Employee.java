package com.casestudy.appraisal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private  String lastName;

    @Column(name="email")
    private String email;

    @Column(name="date_join")
    private Date dateOfJoining;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designation")
    private Designations designation;

    @Column(name="contact_no")
    private String contactNumber;

    @Column(name="role_id")
    private int roleId;

    @Column(name="password")
    private String password;

    @OneToMany(mappedBy = "employeeId", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Task> tasks;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<EmployeeAttributeRating> ratings;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Designations getDesignation() {
        return designation;
    }

    public void setDesignation(Designations designation) {
        this.designation = designation;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<EmployeeAttributeRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<EmployeeAttributeRating> ratings) {
        this.ratings = ratings;
    }

    public Employee(){

    }
    public Employee(String firstName, String lastName, String email, Date dateOfJoining, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfJoining = dateOfJoining;

        this.contactNumber = contactNumber;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfJoining=" + dateOfJoining +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
