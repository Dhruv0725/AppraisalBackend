package com.casestudy.appraisal.controllers;


import com.casestudy.appraisal.DTO.EmployeeDTO;
import com.casestudy.appraisal.DTO.EmployeeDesignatioIDDTO;
import com.casestudy.appraisal.dao.AppDAO;
import com.casestudy.appraisal.entity.Employee;
import com.casestudy.appraisal.entity.LoginEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class LoginController {

    private AppDAO appDAO;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    public LoginController(AppDAO appDao){
        this.appDAO=appDao;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginEntity loginRequest) {
        String userEmail = loginRequest.getUserEmail();
        String password = loginRequest.getPassword();
        System.out.println(userEmail);
        System.out.println(password);

        Employee emp = appDAO.findEmployeeByEmail(userEmail);
        System.out.println(emp);
        if (emp != null && emp.getPassword().equals(password)) {

            EmployeeDTO empDTO=modelMapper.map(emp, EmployeeDTO.class);
            empDTO.setDesignation(emp.getDesignation().getDesignationName());
            // Authentication successful
            return new ResponseEntity<>(empDTO,HttpStatus.OK);
        } else {
            // Authentication failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
