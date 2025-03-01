package com.examly.springapp.service.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exceptions.ContentNotFoundException;
import com.examly.springapp.exceptions.FeedbackNotFoundException;
import com.examly.springapp.exceptions.InvalidFeedbackException;
import com.examly.springapp.exceptions.MaterialNotFoundException;
import com.examly.springapp.exceptions.UserNotFoundException;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.MaterialRepo;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.service.FeedbackService;

// Used with classes that handle business logic.
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepo feedbackRepo;
    private final UserRepo userRepo;
    private final MaterialRepo materialRepo;
    
    @Autowired
    public FeedbackServiceImpl(FeedbackRepo feedbackRepo,UserRepo userRepo,MaterialRepo materialRepo){
        this.materialRepo = materialRepo;
        this.feedbackRepo = feedbackRepo;
        this.userRepo = userRepo;
    }


    @Override
    public Feedback createFeedback(Feedback feedback) throws InvalidFeedbackException, UserNotFoundException, MaterialNotFoundException {
        
        // Validate feedback text
        if (feedback.getFeedbackText() == null || feedback.getFeedbackText().isEmpty()) {
            throw new InvalidFeedbackException("Feedback text cannot be null or empty");
        }

        // Validate user ID
        if (!userRepo.findById(feedback.getUser().getUserId()).isPresent()) {
            throw new UserNotFoundException("User with the given ID not found");
        }

        // Validate material ID
        if (!materialRepo.findById(feedback.getMaterial().getMaterialId()).isPresent()) {
            throw new MaterialNotFoundException("Material with the given ID not found");
        }

        // Set the current date for the feedback
        feedback.setDate(LocalDate.now());

        // Save and return the feedback
        return feedbackRepo.save(feedback);
    }

    @Override
    public Feedback getFeedbackById(Long feedbackId) throws FeedbackNotFoundException {

        // Retrieve feedback by ID
        Feedback feedback = feedbackRepo.findById(feedbackId).orElse(null);
        if (feedback != null) {
            // Return the feedback if found
            return feedback;
        }
        // Throw exception if feedback not found
        throw new FeedbackNotFoundException("Feedback not found");
    }

    @Override
    public List<Feedback> getAllFeedbacks() throws ContentNotFoundException{
        List<Feedback> feedbacks = feedbackRepo.findAll();
        if (feedbacks.isEmpty()) {
            throw new ContentNotFoundException("No Feedback found.");
        }
        return feedbacks;

    }

    @Override
    public Feedback deleteFeedback(Long feedbackId) throws FeedbackNotFoundException {
        // Retrieve feedback by ID
        Feedback feedback = feedbackRepo.findById(feedbackId).orElse(null);
        if (feedback != null) {
            feedbackRepo.deleteById(feedbackId);
            return feedback;
        }
        // Throw exception if feedback not found
        throw new FeedbackNotFoundException("Feedback not found");
    }

    @Override
    public List<Feedback> getFeedbacksByUserId(Long userId) throws UserNotFoundException,ContentNotFoundException {
        // Retrieve user by ID
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            // Return the list of feedbacks by user ID
            List<Feedback> feedbacks =  feedbackRepo.findFeedbacksByUserId(userId);
            if(feedbacks.isEmpty()) throw new ContentNotFoundException("No content found for given user");
            return feedbacks;
        }
 
        // Throw exception if user not found
        throw new UserNotFoundException("User not found");
 
    }
}
