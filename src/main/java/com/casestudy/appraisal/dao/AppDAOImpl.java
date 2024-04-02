package com.casestudy.appraisal.dao;

import com.casestudy.appraisal.DTO.EmployeeDTO;
import com.casestudy.appraisal.DTO.EmployeeDesignatioIDDTO;
import com.casestudy.appraisal.entity.Designations;
import com.casestudy.appraisal.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    @Transactional
    public void create(EmployeeDesignatioIDDTO employee){

        Designations designation = entityManager.find(Designations.class,employee.getDesignation());
        if(designation==null) throw new RuntimeException("Designation not found with ID: " + employee.getDesignation());
        else{
            Employee employeeog = modelMapper.map(employee, Employee.class);
            employeeog.setDesignation(designation);
            entityManager.persist(employeeog);
        }

    }

    @Override
    public EmployeeDTO getEmployeeById(int theId) {

        Employee emp= entityManager.find(Employee.class,theId);

        EmployeeDTO empDTO=modelMapper.map(emp,EmployeeDTO.class);
        empDTO.setDesignation(emp.getDesignation().getDesignationName());
        empDTO.setDesignationId(emp.getDesignation().getId());

        return empDTO;

    }

    @Override
    public Employee getEmployee(int theId) {

        Employee emp= entityManager.find(Employee.class,theId);

        return emp;

    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        TypedQuery<Employee> query=entityManager.createQuery("from Employee",Employee.class);
        List<Employee> employees=query.getResultList();

        return employees.stream()
                .map(employee -> {
                    EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
                    employeeDTO.setDesignation(employee.getDesignation().getDesignationName());
                    return employeeDTO;
                })
                .collect(Collectors.toList());

    }

    public List<EmployeeDTO> getAdminEmployees(){
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.roleId = :id", Employee.class);
        query.setParameter("id", 1);
        List<Employee> employees=query.getResultList();

        return employees.stream()
                .map(employee -> {
                    EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
                    employeeDTO.setDesignation(employee.getDesignation().getDesignationName());
                    return employeeDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.email = :email", Employee.class);
        query.setParameter("email", email);
        System.out.println(query.getResultList().toString());
        try{
            System.out.println(query.getSingleResult());
            System.out.println(query.getResultList().toString());
            return query.getSingleResult();
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    @Transactional
    public void updateEmployee(Employee employeeDTO) {
        Employee employee = entityManager.find(Employee.class, employeeDTO.getId());
        if (employee != null) {
            entityManager.merge(employee);
        } else {
            throw new RuntimeException("Employee not found with ID: " + employeeDTO.getId());
        }
    }


}
