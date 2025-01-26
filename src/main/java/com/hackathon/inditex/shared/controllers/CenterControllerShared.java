package com.hackathon.inditex.shared.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.hackathon.inditex.dtos.DefaultResponse;
import com.hackathon.inditex.entities.Center;
import com.hackathon.inditex.services.CenterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CenterControllerShared {
	
	private final CenterService centerService;

    public ResponseEntity<DefaultResponse> createLogisticsCenter(Center center) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(centerService.createLogisticsCenter(center));
    }
    
    public ResponseEntity<List<Center>> getAllLogisticsCenters() {
    	return ResponseEntity.status(HttpStatus.OK).body(centerService.getAllLogisticsCenters());
    }
    
    public ResponseEntity<DefaultResponse> updateLogisticsCenter(Long id, Center center) {
    	centerService.updateLogisticsCenter(id, center);
        return ResponseEntity.ok(new DefaultResponse("Logistics center updated successfully."));
    }
    
    public ResponseEntity<DefaultResponse> deleteLogisticsCenter(Long id) {
    	centerService.deleteLogisticsCenter(id);
        return ResponseEntity.ok(new DefaultResponse("Logistics center deleted successfully."));
    }
}
