package com.example.farmer_service.farmerController;

import com.example.farmer_service.dto.ApiResponse;
import com.example.farmer_service.farmerEntity.Farmer;
import com.example.farmer_service.farmerService.FarmerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/farmer")
public class FarmerController {

    private static final Logger logger = LoggerFactory.getLogger(FarmerController.class);
    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @PostMapping("/addMilkToFarmer")
    public ResponseEntity<ApiResponse<Farmer>> addMilk(@Valid @RequestBody Farmer farmer) {
        logger.info("Adding milk for farmer: {}", farmer.getFarmerName());
        Farmer saved = farmerService.addMilk(farmer);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Milk added successfully", saved));
    }

    @GetMapping("/allMilk")
    public ResponseEntity<ApiResponse<List<Farmer>>> getAllMilk() {
        logger.info("Fetching all milk data");
        List<Farmer> milkList = farmerService.getAllMilk();
        return ResponseEntity.ok(new ApiResponse<>("All milk data fetched", milkList));
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<ApiResponse<Farmer>> updateStatus(@PathVariable Long id,
                                                            @RequestParam String status) {
        logger.info("Updating milk status for ID: {}", id);
        Farmer updated = farmerService.updateMilkStatus(id, status);
        return ResponseEntity.ok(new ApiResponse<>("Milk status updated", updated));
    }

    @DeleteMapping("/deleteMilk/{id}")
    public ResponseEntity<ApiResponse<String>> deleteMilk(@PathVariable Long id) {
        String result = farmerService.deleteMilk(id);
        return ResponseEntity.ok(new ApiResponse<>(result, null));
    }

}

