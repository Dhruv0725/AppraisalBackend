package com.casestudy.appraisal.dao;

import com.casestudy.appraisal.DTO.EmployeeDTO;
import com.casestudy.appraisal.DTO.EmployeeDesignatioIDDTO;
import com.casestudy.appraisal.entity.Employee;

import java.util.List;

public interface AppDAO {
    void create(EmployeeDesignatioIDDTO employee);

    Employee getEmployee(int theId);
    EmployeeDTO getEmployeeById(int theId);

    List<EmployeeDTO> getAllEmployee();

    Employee findEmployeeByEmail(String email);

    void updateEmployee(Employee employeeDTO);
    List<EmployeeDTO> getAdminEmployees();

}
