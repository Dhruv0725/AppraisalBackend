package com.casestudy.appraisal.controllers;

import com.casestudy.appraisal.DTO.AppraisalDTO;
import com.casestudy.appraisal.DTO.AppraisalDTOList;
import com.casestudy.appraisal.DTO.AppraisalResponseDTO;
import com.casestudy.appraisal.dao.AppraisalDAO;
import com.casestudy.appraisal.dao.TaskDAO;
import com.casestudy.appraisal.entity.Appraisal;
import com.casestudy.appraisal.entity.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//For Employees
@Controller
@CrossOrigin("*")
@RequestMapping("/appraisal")
public class AppraisalController {

    private AppraisalDAO appraisalDAO;
    private TaskDAO taskDAO;
    public AppraisalController(AppraisalDAO appraisalDAO,TaskDAO taskDAO){
        this.appraisalDAO=appraisalDAO;
        this.taskDAO=taskDAO;
    }

    @GetMapping
    public ResponseEntity<List<Appraisal>> getAppraisals(){
        List<Appraisal> appraisals=appraisalDAO.getAllAppraisals();
        return new ResponseEntity<>(appraisals, HttpStatus.OK);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<?> getAppraisedTasksByEmployeeId(@PathVariable int empId){
        List<Appraisal> appraisals=appraisalDAO.getAppraisedTasks(taskDAO.getAppraisalTasks(empId));

        if(appraisals.isEmpty()) return new ResponseEntity<>("No Appraisals yet..",HttpStatus.NOT_FOUND);
        List<AppraisalResponseDTO> apprRsp=appraisals.stream().map(appraisal->{
            AppraisalResponseDTO response=new AppraisalResponseDTO();
            response.setId(appraisal.getId());
            response.setRating(appraisal.getRating());
            response.setTaskTitle(appraisal.getTaskId().getTaskTitle());
            response.setReviewerName(appraisal.getReviewer().getFirstName()+" "+appraisal.getReviewer().getLastName());

            return response;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(apprRsp, HttpStatus.OK);
    }

    @GetMapping("/check/{empId}")
    public ResponseEntity<?> checkIfAppraised(@PathVariable int empId){
        List<Task> appraisals=taskDAO.getAppraisalTasks(empId);

        if(appraisals.isEmpty()) return new ResponseEntity<>(true,HttpStatus.OK);

        return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/addAppraisalTasks/{revId}")
    public ResponseEntity<String> addAppraisal(@PathVariable int revId,@RequestBody AppraisalDTOList theAppraisal){
        List<AppraisalDTO> appraisalDTOS=theAppraisal.getAppraisals();
        System.out.println(appraisalDTOS.toString());
        boolean save=appraisalDAO.addAppraisal(revId,appraisalDTOS);

        if(!save) return new ResponseEntity<>("Not Done", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>("Its done", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delAppraisal(@PathVariable int id){

        appraisalDAO.removeAppraisal(id);
        return new ResponseEntity<>("Its done", HttpStatus.OK);
    }
}
