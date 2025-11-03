package com.example.farmer_service.farmerEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="farmer")
public class Farmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Farmer name is required")
    private String farmerName;

    @NotBlank(message = "Contact number is required")
    private String contactNumber;

    @NotBlank(message = "Location is required")
    private String location;

    @Min(value = 1, message = "Milk quantity must be at least 1 liter")
    private double milkQuantity;

    private double pricePerLiter;
    private String status;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFarmerName() { return farmerName; }
    public void setFarmerName(String farmerName) { this.farmerName = farmerName; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getMilkQuantity() { return milkQuantity; }
    public void setMilkQuantity(double milkQuantity) { this.milkQuantity = milkQuantity; }

    public double getPricePerLiter() { return pricePerLiter; }
    public void setPricePerLiter(double pricePerLiter) { this.pricePerLiter = pricePerLiter; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }


}
