package com.casestudy.appraisal.DTO;

public class RatingDTO {

    int attributeId;
    int rating;

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "RatingDTO{" +
                "attributeId=" + attributeId +
                ", rating=" + rating +
                '}';
    }
}
