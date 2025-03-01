package com.examly.springapp.service.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exceptions.MaterialDeletionException;
import com.examly.springapp.exceptions.MaterialNotFoundException;
import com.examly.springapp.dto.InsightsDTO;
import com.examly.springapp.exceptions.ContentNotFoundException;
import com.examly.springapp.exceptions.UserNotFoundException;
import com.examly.springapp.model.Material;
import com.examly.springapp.model.MaterialRequest;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.MaterialRepo;
import com.examly.springapp.repository.MaterialRequestRepo;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.service.MaterialRequestService;

//Used with classes that handle business logic
@Service
public class MaterialRequestServiceImpl implements MaterialRequestService {

    private final MaterialRequestRepo materialRequestRepo;
    private final UserRepo userRepo;
    private final MaterialRepo materialRepo;

    @Autowired
    public MaterialRequestServiceImpl(MaterialRequestRepo materialRequestRepo, UserRepo userRepo,
            MaterialRepo materialRepo) {
        this.materialRequestRepo = materialRequestRepo;
        this.materialRepo = materialRepo;
        this.userRepo = userRepo;
    }

    @Override
    public MaterialRequest addMaterialRequest(MaterialRequest materialRequest) throws UserNotFoundException,MaterialNotFoundException {
        User user = userRepo.findById(materialRequest.getUser().getUserId()).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User ID not found.");
        }
        Material material = materialRepo.findById(materialRequest.getMaterial().getMaterialId()).orElse(null);
        if (material == null) {
            throw new MaterialNotFoundException("Material ID not found.");
        }
        materialRequest.setStatus("Pending");
        materialRequest.setRequestDate(LocalDate.now());
        materialRequest.setPreferredDeliveryDate(LocalDate.now());

        // Save and return the material request
        return materialRequestRepo.save(materialRequest);
    }

    // Retrieves a material request by its ID and throws a MaterialDeletionException
    // if the requested material had been deleted by the admin
    @Override
    public MaterialRequest getMaterialRequestById(Long materialRequestId) throws MaterialDeletionException {
        MaterialRequest materialRequest = materialRequestRepo.findById(materialRequestId).orElse(null);
        if (materialRequest != null) {
            return materialRequest;
        }
        throw new MaterialDeletionException("Material Request ID is not found.");
    }

    // Retrieves all material requests
    @Override
    public List<MaterialRequest> getAllMaterialRequests() throws ContentNotFoundException {
        List<MaterialRequest> materialRequests = materialRequestRepo.findAll();
        if (materialRequests.isEmpty()) {
            throw new ContentNotFoundException("No material requests found.");
        }
        return materialRequests;
    }

    @Override
    public MaterialRequest updateMaterialRequest(Long materialRequestId, MaterialRequest materialRequest)
            throws MaterialDeletionException {
        MaterialRequest existingRequest = materialRequestRepo.findById(materialRequestId).orElse(null);
        if (existingRequest != null) {

            // Assuming there is a setMaterialRequestId method
            materialRequest.setMaterialRequestId(materialRequestId);

            // Update and save
            return materialRequestRepo.save(materialRequest);
        }
        throw new MaterialDeletionException("Material Request Id is not found.");
    }

    // Deletes a material request by its ID
    @Override
    public Boolean deleteMaterialRequest(Long materialRequestId) throws MaterialDeletionException {
        MaterialRequest materialRequest = materialRequestRepo.findById(materialRequestId).orElse(null);
        if (materialRequest != null) {
            materialRequestRepo.deleteById(materialRequestId);

            // Return true if deletion was successful
            return true;
        }
        throw new MaterialDeletionException("Material Request ID is not found");
    }

   // Retrieves material requests by user ID
    @Override
    public List<MaterialRequest> getMaterialRequestsByUserId(Long userId) throws UserNotFoundException,ContentNotFoundException {
        User user = userRepo.findById(userId).orElse(null);
        if(user == null){
            throw new UserNotFoundException("User not found.");
        }
        List<MaterialRequest> existingRequest = materialRequestRepo.findMaterialRequestsByUserId(userId); 
           
                                                                                                                                                                        
        if (existingRequest.isEmpty()) {

            throw new ContentNotFoundException("With given User ID no material requests are found.");
        }
        return existingRequest;
    }

    // Retrieves user insights, including total requests, high urgency requests, and
    // pending requests
    @Override
    public List<InsightsDTO> getAllUserInsights() {

        List<Object[]> insights = materialRequestRepo.getAllUserInsights();

        List<InsightsDTO> inSightsList = new ArrayList<>();
        for (Object[] row : insights) {
            InsightsDTO insightsDTO = new InsightsDTO((String) row[0], (long) row[1], (long) row[2], (long) row[3]);
            inSightsList.add(insightsDTO);
        } 


        return inSightsList;

    }
}