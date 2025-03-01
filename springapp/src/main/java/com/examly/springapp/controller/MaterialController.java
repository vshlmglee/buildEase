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

import com.examly.springapp.exceptions.ContentNotFoundException;
import com.examly.springapp.exceptions.DuplicateMaterialException;
import com.examly.springapp.exceptions.MaterialNotFoundException;
import com.examly.springapp.model.Material;
import com.examly.springapp.service.MaterialService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;



@Slf4j
// Allow cross-origin requests
@CrossOrigin

// Base URL for all endpoints in this controller
@RequestMapping("/api")

// Marks this class as a REST controller
@RestController

// OpenAPI tag for grouping and describing the controller in Swagger
@Tag(name="Material", description = "Controller for managing materials, including creating, retrieving, updating, and deleting material records.")

public class MaterialController {

    
    private final MaterialService materialService;
    
    @Autowired  // Injecting the MaterialService dependency through constructor injection
    public MaterialController(MaterialService materialService){
        
        this.materialService = materialService;
    }

    @Operation(summary = "Add Material")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201",description = "Material added",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = Material.class))}),
        @ApiResponse(responseCode = "404", description = "Material not found",
        content = @Content)})
    
    @PostMapping("/material") // Endpoint to add a new material
    public ResponseEntity<Material>addMaterial(@Valid @RequestBody Material material) throws DuplicateMaterialException{
        material = materialService.addMaterial(material);
        return ResponseEntity.status(201).body(material);
    }
    
   @Operation(summary = "Material list by Id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Getting Material by Id",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = Material.class))}),
        @ApiResponse(responseCode = "404", description = "Material not found",
        content = @Content)})

    @GetMapping("/material/{materialId}")  // Endpoint to get a material by its ID
    public ResponseEntity<Material>getMaterialById(@PathVariable Long materialId) throws MaterialNotFoundException{
        Material material=materialService.getMaterialById(materialId);
        return ResponseEntity.status(200).body(material);
    }

    @Operation(summary = "Materials")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Retrieves all materials",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = Material.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid",
        content = @Content),
        @ApiResponse(responseCode = "404", description = "List is Empty",
        content = @Content)})
  
    @GetMapping("/material") // Endpoint to get all materials
    public ResponseEntity<List<Material>>getAllMaterials() throws ContentNotFoundException{
        log.info("/api/material");
        List<Material> list = materialService.getAllMaterials();
        return ResponseEntity.status(200).body(list);
        
    }

    @Operation(summary = "Update Material")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Material updated",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = Material.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid",
        content = @Content),
        @ApiResponse(responseCode = "404", description = "Material not found",
        content = @Content)})


    @PutMapping("/material/{materialId}") // Endpoint to update an existing material by its ID
    public ResponseEntity<Material>updateMaterial(@PathVariable Long materialId,@Valid @RequestBody Material material) throws MaterialNotFoundException{
        material = materialService.updateMaterial(materialId,material);
        return ResponseEntity.status(200).body(material);

    }

    @Operation(summary = "Delete Material")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Material with id deleted",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = Material.class))}),
        @ApiResponse(responseCode = "404", description = "Invalid",
        content = @Content),
        @ApiResponse(responseCode = "404", description = "Material not found",
        content = @Content)})
   
    @DeleteMapping("/material/{materialId}")  // Endpoint to delete a material by its ID
    public ResponseEntity<Material>deleteMaterial(@PathVariable Long materialId)throws MaterialNotFoundException {
        Material material = materialService.deleteMaterial(materialId);
        return ResponseEntity.status(200).body(material);
        
    }
}

