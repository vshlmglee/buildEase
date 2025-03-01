package com.examly.springapp.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



// Marks this class as a JPA entity
@Entity
public class Material {

    // Primary key for the Material entity
    @Id
    // Auto-generates the primary key value
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialId;

    // Validates that the material name is not empty and has at least 2 characters
    @NotEmpty(message = "Name cannot be empty.")
    @Size(min=2 ,message="Name should have atleast 2 characters.")
    private String materialName;

    // Validates that the description is not null
    @NotEmpty(message = "Description cannot be null.")
    private String description;

    // Validates that the category is not empty
    @NotEmpty(message="Category cannot be empty.")
    private String category;

    // Validates that the price per unit is not null
    @NotNull(message="Price per unit cannot be empty.")
    private Double pricePerUnit;

    // Validates that the unit type is not empty
    @NotEmpty(message="Unittype cannot be empty.")
    private String unitType;

    // Validates that the availability status is not empty
    @NotEmpty(message="Availabilitystatus cannot be empty.")
    private String availabilityStatus;


    // Default constructor
    public Material() {
    }

    // Parameterized constructor
    public Material(Long materialId, String materialName, String description, String category, Double pricePerUnit,String unitType, String availabilityStatus) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.description = description;
        this.category = category;
        this.pricePerUnit = pricePerUnit;
        this.unitType = unitType;
        this.availabilityStatus = availabilityStatus;
    }

    // Getter and setter methods
    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

}

