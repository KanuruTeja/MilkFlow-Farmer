package com.example.farmer_service.farmerRepo;

import com.example.farmer_service.farmerEntity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepository extends JpaRepository<Farmer,Long> {

}
