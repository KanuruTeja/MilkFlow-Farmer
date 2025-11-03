package com.example.farmer_service.farmerService;


import com.example.farmer_service.exception.ResourceNotFoundException;
import com.example.farmer_service.farmerEntity.Farmer;
import com.example.farmer_service.farmerRepo.FarmerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmerService {

    private static final Logger logger = LoggerFactory.getLogger(FarmerService.class);
    private final FarmerRepository farmerRepository;

    public FarmerService(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    /**
     * Adds a new milk record for a farmer.
     */
    public Farmer addMilk(Farmer farmer) {
        logger.debug("Adding milk record for farmer: {}", farmer.getFarmerName());

        // Business logic: calculate total price dynamically if pricePerLiter not set
        if (farmer.getPricePerLiter() == 0) {
            double baseRate = 45.0; // Assume Rs.45 per liter by default
            farmer.setPricePerLiter(baseRate);
        }

        // Default status
        farmer.setStatus("AVAILABLE");

        Farmer savedFarmer = farmerRepository.save(farmer);
        logger.info("Milk record saved successfully for farmer ID: {}", savedFarmer.getId());
        return savedFarmer;
    }

    /**
     * Fetches all milk records.
     */
    public List<Farmer> getAllMilk() {
        logger.info("Fetching all milk records");
        List<Farmer> farmers = farmerRepository.findAll();

        if (farmers.isEmpty()) {
            throw new ResourceNotFoundException("No milk records found");
        }

        return farmers;
    }

    /**
     * Updates the status of a farmer’s milk batch.
     * Possible statuses: AVAILABLE, ASSIGNED, DELIVERED, SOLD
     */
    public Farmer updateMilkStatus(Long id, String status) {
        logger.info("Updating milk status for farmer ID: {}", id);

        Farmer farmer = farmerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Farmer record not found with ID: " + id));

        // Validate allowed statuses
        if (!List.of("AVAILABLE", "ASSIGNED", "DELIVERED", "SOLD").contains(status.toUpperCase())) {
            throw new IllegalArgumentException("Invalid status. Allowed values: AVAILABLE, ASSIGNED, DELIVERED, SOLD");
        }

        farmer.setStatus(status.toUpperCase());
        Farmer updated = farmerRepository.save(farmer);
        logger.info("Updated milk status for farmer ID {} to {}", id, status);
        return updated;
    }

    /**
     * Delete milk record (optional admin use case).
     */
    public String deleteMilk(Long id) {
        logger.info("Deleting milk record for farmer ID: {}", id);
        Farmer farmer = farmerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Farmer record not found with ID: " + id));

        farmerRepository.delete(farmer);
        logger.info("Milk record deleted for farmer ID: {}", id);
        return "Milk record deleted successfully for farmer ID: " + id;
    }
}
