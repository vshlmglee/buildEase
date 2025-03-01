package com.examly.springapp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// Marks this class as a JPA entity
@Entity
public class MaterialRequest {

    // Primary key for the MaterialRequest entity
    @Id

    // Auto-generates the primary key value
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialRequestId;

    // Many-to-one relationship with User entity
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    // Many-to-one relationship with Material entity
    @ManyToOne
    @JoinColumn(name = "materialId")
    private Material material;

    // Validates that the quantity is not null and must be positive


    @NotNull(message = "Quantity can't be null.")
    @Positive(message = "Quantity must be positive.")
    private Double quantity;

    // Validates that the urgency level is not empty
    @NotEmpty(message = "Urgency level can't be empty.")
    private String urgencyLevel;

    // Validates that the request date is not null
    @NotNull(message = "Request date can't be null.")
    private LocalDate requestDate;

    // Validates that the preferred delivery date is not null
    @NotNull(message = "Preferred delivery date can't be null.")
    private LocalDate preferredDeliveryDate;

    // Validates that the time slot is not empty
    @NotEmpty(message = "Time slot can't be empty.")
    private String timeSlot;

    // Validates that the status is not empty
    @NotEmpty(message = "Status can't be empty.")
    private String status;

    // Validates that the delivery address is not empty
    @NotEmpty(message = "Delivery address can't be empty.")
    private String deliveryAddress;

    // Validates that the contact number is not empty and must be a 10-digit number
    @Pattern(regexp = "^[\\d]{10}$",message = "Mobile Number must be of 10 digits.")
    @NotEmpty(message = "Mobile number is required.")
    private String contactNumber;


    // Validates that comments are not empty
    @NotEmpty(message = "Comments are required.")
    private String comments;

    // Default constructor
    public MaterialRequest(){
        log.info("in constructor");
    }


    // Getter and setter methods

    public Long getMaterialRequestId() {
        return materialRequestId;
    }


    public void setMaterialRequestId(Long materialRequestId) {
        this.materialRequestId = materialRequestId;
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


    public Double getQuantity() {
        return quantity;
    }


    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }


    public String getUrgencyLevel() {
        return urgencyLevel;
    }


    public void setUrgencyLevel(String urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }


    public LocalDate getRequestDate() {
        return requestDate;
    }


    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }


    public LocalDate getPreferredDeliveryDate() {
        return preferredDeliveryDate;
    }


    public void setPreferredDeliveryDate(LocalDate preferredDeliveryDate) {
        this.preferredDeliveryDate = preferredDeliveryDate;
    }


    public String getTimeSlot() {
        return timeSlot;
    }


    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getDeliveryAddress() {
        return deliveryAddress;
    }


    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }


    public String getContactNumber() {
        return contactNumber;
    }


    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


    public String getComments() {
        return comments;
    }


    public void setComments(String comments) {
        this.comments = comments;
    }

    //remove this
    @Override
    public String toString() {
        return "MaterialRequest [materialRequestId=" + materialRequestId + ", user=" + user + ", material=" + material
                + ", quantity=" + quantity + ", urgencyLevel=" + urgencyLevel + ", requestDate=" + requestDate
                + ", preferredDeliveryDate=" + preferredDeliveryDate + ", timeSlot=" + timeSlot + ", status=" + status
                + ", deliveryAddress=" + deliveryAddress + ", contactNumber=" + contactNumber + ", comments=" + comments
                + "]";
    }

    
    
    
}