package com.examly.springapp.service.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exceptions.ContentNotFoundException;
import com.examly.springapp.exceptions.DuplicateMaterialException;
import com.examly.springapp.exceptions.MaterialNotFoundException;
import com.examly.springapp.model.Material;
import com.examly.springapp.repository.MaterialRepo;
import com.examly.springapp.service.MaterialService;

//Used with classes that handle business logic
@Service
public class MaterialServiceImpl implements MaterialService{

    private final MaterialRepo materialRepo;
    
    @Autowired
    public MaterialServiceImpl(MaterialRepo materialRepo){
        this.materialRepo = materialRepo;
    }
    
    @Override
    public Material addMaterial(Material material) throws DuplicateMaterialException{
        Material existingMaterial = materialRepo.findByMaterialName(material.getMaterialName());
        if (existingMaterial != null) {
            throw new DuplicateMaterialException("Material already exists!");
        }
        material = materialRepo.save(material);
        return material;
    }

    // Retrieves a material by its ID and throws material not found exception if the material with that ID is not found
    @Override
    public Material getMaterialById(Long materialId) throws MaterialNotFoundException {
        Material material = materialRepo.findById(materialId).orElse(null);
        if (material != null) {
            return material;
        }
        throw new MaterialNotFoundException("Material not found!");
    }

    // Retrieves all materials
    @Override
    public List<Material> getAllMaterials() throws ContentNotFoundException{
        List<Material> materials = materialRepo.findAll();
        if (materials.isEmpty()) {
            throw new ContentNotFoundException("No material found.");
        }
        return materials;
    }

    // Updates an existing material by its ID
    @Override
    public Material updateMaterial(Long materialId, Material material) throws MaterialNotFoundException{
        Material existingMaterial = materialRepo.findById(materialId).orElse(null);
        if (existingMaterial == null) {
            throw new MaterialNotFoundException("Material with given ID not found");
        }
        material.setMaterialId(materialId);
        return materialRepo.save(material);
    
 
    }

    // Deletes a material by its ID and throws material not found exception if the material with that ID is not found
    @Override
    public Material deleteMaterial(Long materialId) throws MaterialNotFoundException{
        Material material = materialRepo.findById(materialId).orElse(null);
        if(material==null) 
        {
            throw new MaterialNotFoundException("Material not found");
        }
        materialRepo.deleteFeedbacksByMaterialId(materialId);
        materialRepo.deleteMaterialRequestsByMaterialId(materialId);
        materialRepo.delete(material); 

        return material;
    }
}