package com.casestudy.appraisal.DTO;


import java.util.List;

public class EmployeeRatingListDTO {

    private List<RatingDTO> ratings;

    public List<RatingDTO> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingDTO> ratings) {
        this.ratings = ratings;
    }
}
