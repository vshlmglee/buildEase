package com.examly.springapp.service;
import java.util.List;

import com.examly.springapp.exceptions.ContentNotFoundException;
import com.examly.springapp.exceptions.FeedbackNotFoundException;
import com.examly.springapp.exceptions.InvalidFeedbackException;
import com.examly.springapp.exceptions.MaterialNotFoundException;
import com.examly.springapp.exceptions.UserNotFoundException;
import com.examly.springapp.model.Feedback;

public interface FeedbackService {
    

    // Method to create new feedback
    Feedback createFeedback(Feedback feedback) throws InvalidFeedbackException, UserNotFoundException, MaterialNotFoundException;

    // Method to get feedback by ID
    Feedback getFeedbackById(Long feedBackId) throws FeedbackNotFoundException;

    // Method to get all feedbacks
    List<Feedback> getAllFeedbacks() throws ContentNotFoundException;


    // Method to delete feedback by ID
    Feedback deleteFeedback(Long feedBackId) throws FeedbackNotFoundException;

    // Method to get feedbacks by user ID
    List<Feedback> getFeedbacksByUserId(Long userId) throws UserNotFoundException,ContentNotFoundException;
}
