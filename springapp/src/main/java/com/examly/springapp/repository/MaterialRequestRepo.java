package com.examly.springapp.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
import com.examly.springapp.model.MaterialRequest;

//Handles storing and fetching data from a database.
@Repository
public interface MaterialRequestRepo extends JpaRepository<MaterialRequest, Long> {

    // JPQL query method to find material requests by user ID
    @Query("select m from MaterialRequest m where m.user.userId=?1")
    List<MaterialRequest> findMaterialRequestsByUserId(Long userId);

    // JPQL query method to get user insights, including total requests, high urgency requests, and pending requests
    @Query("SELECT mr.user.username, COUNT(mr), SUM(CASE WHEN mr.urgencyLevel = 'High' THEN 1 ELSE 0 END), SUM(CASE WHEN mr.status = 'Pending' THEN 1 ELSE 0 END) FROM MaterialRequest mr GROUP BY mr.user.userId")
    List<Object[]> getAllUserInsights();
}