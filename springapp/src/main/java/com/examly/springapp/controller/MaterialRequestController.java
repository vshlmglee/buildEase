package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.dto.InsightsDTO;
import com.examly.springapp.exceptions.ContentNotFoundException;
import com.examly.springapp.exceptions.MaterialDeletionException;
import com.examly.springapp.exceptions.MaterialNotFoundException;
import com.examly.springapp.exceptions.UserNotFoundException;
import com.examly.springapp.model.MaterialRequest;
import com.examly.springapp.service.MaterialRequestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

// Allow cross-origin requests
@CrossOrigin

// Marks this class as a REST controller
@RestController

// Base URL for all endpoints in this controller
@RequestMapping("/api")

// OpenAPI tag for grouping and describing the controller
@Tag(name="MaterialRequest", description = "Controller for managing material requests, including creating, retrieving, updating, and deleting material request records, as well as providing user insights.")

public class MaterialRequestController {

    private final MaterialRequestService materialRequestService;
    
 
    @Autowired    // Injects the MaterialRequestService dependency through constructor injection
    public MaterialRequestController(MaterialRequestService materialRequestService){
        this.materialRequestService = materialRequestService;
    }

    @Operation(summary = "Add MaterialRequest")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201",description = "MaterialRequest is added",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = MaterialRequest.class))}),
        @ApiResponse(responseCode="400", description = "Invalid MaterialRequest",
        content = @Content),
        @ApiResponse(responseCode = "404", description = "MaterialRequest not found",
        content = @Content)})

    @PostMapping("/materialRequest") // Endpoint to add a new material request
    public ResponseEntity<MaterialRequest> addMaterialRequest(@Valid @RequestBody MaterialRequest materialRequest)throws Exception{
        MaterialRequest mr=materialRequestService.addMaterialRequest(materialRequest);
        return ResponseEntity.status(201).body(mr);
    }

    @Operation(summary = "MaterialRequests")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201",description = "Retrieves MaterialRequests",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = MaterialRequest.class))}),
        @ApiResponse(responseCode="400", description = "Invalid Feedback",
        content = @Content),
        @ApiResponse(responseCode = "404", description = "User not found",
        content = @Content)})

    @GetMapping("/materialRequest/{materialRequestId}") // Endpoint to get a material request by its ID
    public ResponseEntity<MaterialRequest> getMaterialRequestById(@PathVariable Long materialRequestId) throws MaterialDeletionException{
        MaterialRequest mr1=materialRequestService.getMaterialRequestById(materialRequestId);
        return ResponseEntity.status(200).body(mr1);
    }

    @Operation(summary = "MaterialRequests")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Retrieves MaterialRequests By Id",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = MaterialRequest.class))}),
        @ApiResponse(responseCode = "404", description = "User not found",
        content = @Content)})

    @GetMapping("/materialRequest/user/{userId}") // Endpoint to get material requests by user ID
    public ResponseEntity<List<MaterialRequest>> getMaterialRequestsByUserId(@PathVariable Long userId)throws UserNotFoundException,ContentNotFoundException {
        List<MaterialRequest> list=materialRequestService.getMaterialRequestsByUserId(userId);
        return ResponseEntity.status(200).body(list);
    }

 
    @Operation(summary = "MaterialRequests")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Retrieves MaterialRequests",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = MaterialRequest.class))}),
        @ApiResponse(responseCode = "404", description = "User not found",
        content = @Content)})

    @GetMapping("/materialRequest") // Endpoint to get all material requests
    public ResponseEntity<List<MaterialRequest>> getAllMaterialRequests() throws ContentNotFoundException{
        List<MaterialRequest> list = materialRequestService.getAllMaterialRequests();
        return ResponseEntity.status(200).body(list);
    }

    
    @Operation(summary = "MaterialRequests")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "MaterialRequests Updation",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = MaterialRequest.class))}),
        @ApiResponse(responseCode = "404", description = "NotFound",
        content = @Content)})

    @PutMapping("/materialRequest/{materialRequestId}") // Endpoint to update an existing material request by its ID
    public ResponseEntity<MaterialRequest> updateMaterialRequest(@PathVariable Long materialRequestId, @RequestBody MaterialRequest materialRequest) throws MaterialDeletionException{
        MaterialRequest mr1=materialRequestService.updateMaterialRequest(materialRequestId,materialRequest);
        return ResponseEntity.status(200).body(mr1);
    }

    
    @Operation(summary = "MaterialRequests")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "MaterialRequests deletion",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = MaterialRequest.class))}),
        @ApiResponse(responseCode = "404", description = "Invalid",
        content = @Content)})

    @DeleteMapping("/materialRequest/{materialRequestId}") // Endpoint to delete a material request by its ID
    public ResponseEntity<Boolean> deleteMaterialRequest(@PathVariable Long materialRequestId)throws MaterialDeletionException{
        boolean result=materialRequestService.deleteMaterialRequest(materialRequestId);
        return ResponseEntity.status(200).body(result);
    }

    @Operation(summary = "MaterialRequests")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "MaterialRequest Insights",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = MaterialRequest.class))}),
        @ApiResponse(responseCode = "404", description = "Invalid",
        content = @Content)})


    

    
    @GetMapping("/materialRequest/admin/insights") // Endpoint to get all user insights for admin
    public ResponseEntity<List<InsightsDTO>> getAllUserInsights(){
        List<InsightsDTO> lists=materialRequestService.getAllUserInsights();
        return ResponseEntity.status(200).body(lists);
    }
    

    
}