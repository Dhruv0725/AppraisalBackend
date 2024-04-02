package com.casestudy.appraisal.Services;

import com.casestudy.appraisal.DTO.EmployeeDTO;
import com.casestudy.appraisal.DTO.RatingDTO;
import com.casestudy.appraisal.DTO.RatingNameDTO;
import com.casestudy.appraisal.dao.*;
import com.casestudy.appraisal.embeddable.EmployeeAttributeRatingId;
import com.casestudy.appraisal.entity.Attributes;
import com.casestudy.appraisal.entity.Employee;
import com.casestudy.appraisal.entity.EmployeeAttributeRating;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AttributeRatingServiceImpl implements AttributeRatingService{


    private AppDAO appDAO;
    private AttributeDAO attributeDAO;

    private RatingDAO ratingDAO;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    public AttributeRatingServiceImpl(AppDAO appDAO, AttributeDAO attributeDAO, RatingDAO ratingDAO){
        this.appDAO=appDAO;
        this.attributeDAO=attributeDAO;
        this.ratingDAO=ratingDAO;
    }
    public boolean saveEmployeeAttributeRatings(int employeeId, List<RatingDTO> ratings) {
        if (!validateEmployeeExistence(employeeId)) {
            return false; // Return false if employee does not exist
        }

        Set<Integer> existingAttributeIds = getExistingAttributeIdsForEmployee(employeeId);

        for (RatingDTO ratingDTO : ratings) {
            int attributeId = ratingDTO.getAttributeId();
            int rating = ratingDTO.getRating();

            if (!validateAttributeExistence(attributeId) || existingAttributeIds.contains(attributeId)) {
                continue;
            }

            EmployeeAttributeRatingId id = new EmployeeAttributeRatingId();
            id.setEmployeeId(employeeId);
            id.setAttributeId(attributeId);
            System.out.println(id);
            EmployeeAttributeRating employeeAttributeRating = new EmployeeAttributeRating();
            employeeAttributeRating.setId(id);
            employeeAttributeRating.setRating(rating);
            Employee emp=appDAO.getEmployee(employeeId);
            Attributes att=attributeDAO.getByAttributeId(attributeId);

            employeeAttributeRating.setEmployee(emp);
            employeeAttributeRating.setAttribute(att);

            emp.getRatings().add(employeeAttributeRating);
            att.getRatings().add(employeeAttributeRating);

            appDAO.updateEmployee(emp);
        }

        return true;
    }

    public List<RatingNameDTO> getRatingsById(int id){

        List<EmployeeAttributeRating> ratings= ratingDAO.getByEmployeeId(id);

        return ratings.stream().map(rating ->{
            RatingNameDTO rt=new RatingNameDTO();
            rt.setId(rating.getId().getEmployeeId());
            rt.setAttribute(attributeDAO.getByAttributeId(rating.getId().getAttributeId()).getAttributeName());
            rt.setRating(rating.getRating());

            return rt;
        }).collect(Collectors.toList());
    }

    private boolean validateEmployeeExistence(int employeeId) {
        EmployeeDTO emp = appDAO.getEmployeeById(employeeId);
        return emp!=null;
    }

    private boolean validateAttributeExistence(int attributeId) {
        Attributes att = attributeDAO.getByAttributeId(attributeId);
        return att!=null;
    }

    private Set<Integer> getExistingAttributeIdsForEmployee(int employeeId) {
        List<EmployeeAttributeRating> ratings = ratingDAO.getByEmployeeId(employeeId);
        return ratings.stream().map(rating -> rating.getId().getAttributeId()).collect(Collectors.toSet());
    }

}
