package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Feedback;

// Handles storing and fetching data from a database.
@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Long> {

    // Custom query to find feedbacks by user ID
    @Query("select c from Feedback c where c.user.userId = ?1")
    List<Feedback> findFeedbacksByUserId(Long userId);
}
