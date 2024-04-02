package com.casestudy.appraisal.controllers;

import com.casestudy.appraisal.dao.DesignationDAO;
import com.casestudy.appraisal.entity.Designations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/designations")
public class DesignationController {

    private DesignationDAO designationDAO;

    @Autowired
    public DesignationController(DesignationDAO designationDAO){
        this.designationDAO=designationDAO;
    }
    @GetMapping
    public ResponseEntity<List<Designations>> getDesignations(){
       List<Designations>designations= designationDAO.getDesignations();
       return new ResponseEntity<>(designations, HttpStatus.OK);
    }
}
