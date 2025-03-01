package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.dto.InsightsDTO;
import com.examly.springapp.exceptions.ContentNotFoundException;
import com.examly.springapp.exceptions.MaterialDeletionException;
import com.examly.springapp.exceptions.MaterialNotFoundException;
import com.examly.springapp.exceptions.UserNotFoundException;
import com.examly.springapp.model.MaterialRequest;

public interface MaterialRequestService {

    // Adds a new material request
    MaterialRequest addMaterialRequest(MaterialRequest materialRequest)throws UserNotFoundException,MaterialNotFoundException;
    
    // Retrieves a material request by its ID 
    MaterialRequest getMaterialRequestById(Long materialRequestId) throws MaterialDeletionException;
  
    // Retrieves all material requests
    List<MaterialRequest> getAllMaterialRequests() throws ContentNotFoundException;


    // Updates an existing material request by its ID
    MaterialRequest updateMaterialRequest(Long materialRequestId, MaterialRequest materialRequest)throws MaterialDeletionException;

    // Deletes a material request by its ID
    Boolean deleteMaterialRequest(Long materialRequestId) throws MaterialDeletionException;

    // Retrieves material requests by user ID
    List<MaterialRequest> getMaterialRequestsByUserId(Long userId)throws UserNotFoundException,ContentNotFoundException ;

    // Retrieves user insights, including total requests, high urgency requests, and pending requests
    List<InsightsDTO> getAllUserInsights();

}
