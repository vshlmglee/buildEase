package com.examly.springapp.exceptions;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Used to handle exceptions across an entire application
@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle DuplicateMaterialException and return a 409 status code
    @ExceptionHandler(DuplicateMaterialException.class)
    public ResponseEntity<String> handleDuplicateMaterialException(DuplicateMaterialException e) {
        return ResponseEntity.status(409).body(e.getMessage());
    }

    // Handle MaterialDeletionException and return a 400 status code
    @ExceptionHandler(MaterialDeletionException.class)
    public ResponseEntity<String> handleMaterialDeletionException(MaterialDeletionException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    // Handle MaterialRequestException and return a 400 status code
    @ExceptionHandler(MaterialRequestException.class)
    public ResponseEntity<String> handleMaterialRequestException(MaterialRequestException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    // Handle UserAlreadyExistsException and return a 409 status code
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return ResponseEntity.status(409).body(e.getMessage());
    }

    // Handle UserNotFoundException and return a 404 status code
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    // Handle FeedbackNotFoundException and return a 404 status code
    @ExceptionHandler(FeedbackNotFoundException.class)
    public ResponseEntity<String> handleFeedbackNotFoundException(FeedbackNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    // Handle InvalidFeedbackException and return a 400 status code
    @ExceptionHandler(InvalidFeedbackException.class)
    public ResponseEntity<String> handleInvalidFeedbackException(InvalidFeedbackException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    // Handle MaterialNotFoundException and return a 404 status code
    @ExceptionHandler(MaterialNotFoundException.class)
    public ResponseEntity<String> handleMaterialNotFoundException(MaterialNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    // Handle ContentNotFoundException and return a 204 status code
    @ExceptionHandler(ContentNotFoundException.class)
    public ResponseEntity<String> handleContentNotFoundException(ContentNotFoundException e) {
        return ResponseEntity.status(204).body(e.getMessage()); // though user is authenticated but there is no content
                                                                // on server
    }

    // Handle MethodArgumentNotValidException and return a 400 status code
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        Map<String, String> map = new HashMap<>();
        // Collect field errors and add them to the map
        e.getBindingResult().getFieldErrors().forEach(error -> map.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(400).body(map.toString());
    }
}
