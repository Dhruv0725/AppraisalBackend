package com.casestudy.appraisal.controllers;

import com.casestudy.appraisal.DTO.EmployeeDTO;
import com.casestudy.appraisal.DTO.EmployeeDesignatioIDDTO;
import com.casestudy.appraisal.dao.AppDAO;
import com.casestudy.appraisal.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/employee")
public class EmployeeController {

    private AppDAO appDAO;

    @Autowired
    public EmployeeController(AppDAO appDao){
        this.appDAO=appDao;
    }
    @PostMapping("/createEmployee")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDesignatioIDDTO theEmployee){
        theEmployee.setId(0);
        Employee emp = appDAO.findEmployeeByEmail(theEmployee.getEmail());
        System.out.println("Here we are");
        System.out.println(emp);
        if(emp!=null){
            return new ResponseEntity<>("Employee email already exists", HttpStatus.CONFLICT);
        }
        appDAO.create(theEmployee);
        return new ResponseEntity<>("emp",HttpStatus.OK);
    }

    @GetMapping("/getEmployees")
    public ResponseEntity<List<EmployeeDTO>> getEmployees(){
        List<EmployeeDTO> employees=appDAO.getAllEmployee();

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    @GetMapping("/{empId}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable int empId){
        EmployeeDTO emp=appDAO.getEmployeeById(empId);
        return new ResponseEntity<>(emp,HttpStatus.OK);
    }

    @GetMapping("/findbyemail")
    public ResponseEntity<Employee> getEmployeeByMail(@RequestBody Employee theEmployee){
        System.out.println(theEmployee.getEmail());
        Employee emp = appDAO.findEmployeeByEmail(theEmployee.getEmail());
        return new ResponseEntity<>(emp,HttpStatus.OK);
    }

    @GetMapping("/admins")
    public  ResponseEntity<?> getAdmins(){
        List<EmployeeDTO> employeeDTOS=appDAO.getAdminEmployees();

        return new ResponseEntity<>(employeeDTOS,HttpStatus.OK);
    }


}
