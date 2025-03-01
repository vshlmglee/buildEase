package com.examly.springapp.model;
 
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

 
// Specifies that the class is an entity and is mapped to a database table
@Entity
public class Feedback {
    
    
    @Id // Specifies the primary key of an entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the primary key generation strategy
    private Long feedBackId;
    
     
    @NotEmpty(message = "Feedback text can't be empty.") // Validation annotation to check if the field is not empty
    @Size(min=5, message="Name should have at least 5 characters.") // Validation annotation to check the size of the field
    private String feedbackText;

    private LocalDate date;

    
    @ManyToOne // Specifies a many-to-one relationship
    @JoinColumn(name = "userId", nullable = false)
    private User user; 

    // Specifies a many-to-one relationship
    @ManyToOne

    // Specifies the foreign key column
    @JoinColumn(name = "materialId", nullable = true)
    private Material material;

    // Validation annotation to check if the field is not empty
    @NotEmpty(message = "Category can't be empty.")
    private String category;

    // Default constructor
    public Feedback() {
    }

    // Parameterized constructor
    public Feedback(Long feedBackId, String feedbackText, LocalDate date, User user, Material material, String category) {
        this.feedBackId = feedBackId;
        this.feedbackText = feedbackText;
        this.date = date;
        this.user = user;
        this.material = material;
        this.category = category;
    }

    //Getter and Setter methods
    public Long getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(Long feedBackId) {
        this.feedBackId = feedBackId;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
