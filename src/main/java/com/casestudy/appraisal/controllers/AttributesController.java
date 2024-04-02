package com.casestudy.appraisal.controllers;

import com.casestudy.appraisal.DTO.AttributeDTO;
import com.casestudy.appraisal.dao.DesignationDAO;
import com.casestudy.appraisal.entity.Attributes;
import com.casestudy.appraisal.entity.Designations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/attributes")
public class AttributesController {

    @Autowired
    private ModelMapper modelMapper;
    private DesignationDAO designationDAO;

    @Autowired
    public AttributesController(DesignationDAO designationDAO){
        this.designationDAO=designationDAO;

    }

    @GetMapping("/{desId}")
    public ResponseEntity<?> getAttributesByDesignationId(@PathVariable int desId){
        Designations designations=designationDAO.getDesignations(desId);

        if(designations==null) return new ResponseEntity<>("No such designation exist", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(designations.getAttributes().stream().map(attributes->modelMapper.map(attributes, AttributeDTO.class)),HttpStatus.OK);
    }

}
