package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.exceptions.ContentNotFoundException;
import com.examly.springapp.exceptions.FeedbackNotFoundException;
import com.examly.springapp.exceptions.InvalidFeedbackException;
import com.examly.springapp.exceptions.MaterialNotFoundException;
import com.examly.springapp.exceptions.UserNotFoundException;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.service.FeedbackService;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController // Marks this class as a RESTful controller
@Tag(name = "Feedback", description = "Endpoints for feedback management.") // Swagger tag to describe this controller
@RequestMapping("/api") // Base URL for all the endpoints in this controller
public class FeedbackController {

    private final FeedbackService feedbackService;

    // @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Operation(summary = "Add Feedback")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201",description = "Feedback added",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = Feedback.class))}),
        @ApiResponse(responseCode="400", description = "Invalid Feedback",
        content = @Content),
        @ApiResponse(responseCode = "404", description = "User not found",
        content = @Content)})

    @PostMapping("/feedback") // Endpoint to create new feedback
    public ResponseEntity<Feedback> createFeedback(@Valid @RequestBody Feedback feedback)
            throws InvalidFeedbackException, UserNotFoundException, MaterialNotFoundException {

        feedback = feedbackService.createFeedback(feedback);
        return ResponseEntity.status(201).body(feedback);
    }

    @Operation(summary = "Get Feedbacks")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Feedback List",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = Feedback.class))}),
        @ApiResponse(responseCode = "404", description = "Feedback not found",
        content = @Content)})

    @GetMapping("/feedback/{feedbackId}") // Endpoint to get feedback by ID
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long feedbackId) throws FeedbackNotFoundException {
        Feedback feedback = feedbackService.getFeedbackById(feedbackId);
        return ResponseEntity.status(200).body(feedback);
    }

    @Operation(summary = "Get Feedback")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Feedback List",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = Feedback.class))}),
        @ApiResponse(responseCode="400", description = "Content Not Found",
        content = @Content)})

    @GetMapping("/feedback") // Endpoint to get all feedbacks
    public ResponseEntity<List<Feedback>> getAllFeedbacks() throws ContentNotFoundException {
        List<Feedback> list = feedbackService.getAllFeedbacks();
        return ResponseEntity.status(200).body(list);
    }

    @Operation(summary = "Feedback list by UserId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Feedback",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = Feedback.class))}),
        @ApiResponse(responseCode = "404", description = "User not found",
        content = @Content)})

    @GetMapping("/feedback/user/{userId}") // Endpoint to get feedbacks by user ID
    public ResponseEntity<List<Feedback>> getFeedbacksByUserId(@PathVariable Long userId) throws UserNotFoundException,ContentNotFoundException {
        List<Feedback> list = feedbackService.getFeedbacksByUserId(userId);
        return ResponseEntity.status(200).body(list);
    }

    @Operation(summary = "Delete Feedback")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Feedback deleted",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = Feedback.class))}),
        @ApiResponse(responseCode = "404", description = "Feedback not found",
        content = @Content)})
        
    @DeleteMapping("/feedback/{feedbackId}") // Endpoint to delete feedback by ID
    public ResponseEntity<Feedback> deleteFeedback(@PathVariable Long feedbackId) throws FeedbackNotFoundException {
        Feedback feedback = feedbackService.deleteFeedback(feedbackId);
        return ResponseEntity.status(200).body(feedback);
    }
}
