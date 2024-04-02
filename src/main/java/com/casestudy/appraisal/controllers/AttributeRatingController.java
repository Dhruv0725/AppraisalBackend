package com.casestudy.appraisal.controllers;

import com.casestudy.appraisal.DTO.EmployeeRatingListDTO;
import com.casestudy.appraisal.DTO.RatingDTO;
import com.casestudy.appraisal.DTO.RatingNameDTO;
import com.casestudy.appraisal.Services.AttributeRatingService;
import com.casestudy.appraisal.entity.EmployeeAttributeRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/attributes/ratings")
public class AttributeRatingController {

    @Autowired
    private AttributeRatingService attributeRatingService;
    @PostMapping("/{empId}")
    public ResponseEntity<String> saveEmployeeAttributeRatings(@PathVariable int empId, @RequestBody EmployeeRatingListDTO empList){
        List<RatingDTO> ratings=empList.getRatings();
        System.out.println(empId);
        System.out.println(ratings.toString());
        boolean saved=attributeRatingService.saveEmployeeAttributeRatings(empId,ratings);

        if(saved){
            return new ResponseEntity<>("Succesfully Rated Attributes", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Attribute Rating Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{empId}")
    public ResponseEntity<?> getAttributeRatingsById(@PathVariable int empId){

        List<RatingNameDTO> ratings=attributeRatingService.getRatingsById(empId);

        if(ratings!=null && !ratings.isEmpty())
        return new ResponseEntity(ratings,HttpStatus.OK);
        else return new ResponseEntity<>("No attributes rated for employee",HttpStatus.BAD_REQUEST);
    }
}
