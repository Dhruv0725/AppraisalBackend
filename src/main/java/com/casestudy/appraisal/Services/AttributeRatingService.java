package com.casestudy.appraisal.Services;

import com.casestudy.appraisal.DTO.RatingDTO;
import com.casestudy.appraisal.DTO.RatingNameDTO;
import com.casestudy.appraisal.entity.EmployeeAttributeRating;

import java.util.List;

public interface AttributeRatingService {
    boolean saveEmployeeAttributeRatings(int employeeId, List<RatingDTO> ratings);
    List<RatingNameDTO> getRatingsById(int id);
}
