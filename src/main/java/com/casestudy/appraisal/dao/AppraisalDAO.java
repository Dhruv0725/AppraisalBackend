package com.casestudy.appraisal.dao;

import com.casestudy.appraisal.DTO.AppraisalDTO;
import com.casestudy.appraisal.entity.Appraisal;
import com.casestudy.appraisal.entity.Task;

import java.util.List;

public interface AppraisalDAO {

    List<Appraisal> getAllAppraisals();

    Appraisal getAppraisal(int id);

    boolean addAppraisal(int revId,List<AppraisalDTO> appraisalDTOS);

    void removeAppraisal(int id);

    List<Appraisal> getAppraisedTasks(List<Task> tasks);
}
