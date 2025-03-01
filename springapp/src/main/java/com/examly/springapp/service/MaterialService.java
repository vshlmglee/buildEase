package com.examly.springapp.service;
import java.util.List;

import com.examly.springapp.exceptions.ContentNotFoundException;
import com.examly.springapp.exceptions.DuplicateMaterialException;
import com.examly.springapp.exceptions.MaterialNotFoundException;
import com.examly.springapp.model.Material;

public interface MaterialService {

    // Adds a new material and throws a DuplicateMaterialException if material with that same name already exists.
    Material addMaterial(Material material) throws DuplicateMaterialException;

    // Retrieves a material by its ID and throws a excecption if the ID is not found.
    Material getMaterialById(Long materialId) throws MaterialNotFoundException;

    // Retrieves all materials.
    List<Material> getAllMaterials() throws ContentNotFoundException;
    
    // Updates an existing material by its ID.
    Material updateMaterial(Long materialId, Material material)  throws MaterialNotFoundException;

    // Deletes a material by its ID and throws a excecption if the ID is not found.
    Material deleteMaterial(Long materialId) throws MaterialNotFoundException;
}
