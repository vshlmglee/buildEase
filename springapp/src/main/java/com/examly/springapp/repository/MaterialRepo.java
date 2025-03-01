package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.examly.springapp.model.Material;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;


//Handles storing and fetching data from a database.
@Repository
public interface MaterialRepo extends JpaRepository <Material,Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Feedback f WHERE f.material.materialId = ?1")
    void deleteFeedbacksByMaterialId(Long materialId);

    @Modifying
    @Transactional
    @Query("DELETE FROM MaterialRequest mr WHERE mr.material.materialId = ?1")
    void deleteMaterialRequestsByMaterialId(Long materialId);

    // Custom query method to find a Material by its name
    Material findByMaterialName(String materialName);

}
