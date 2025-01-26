package com.hackathon.inditex.services;

import java.util.List;

import com.hackathon.inditex.dtos.DefaultResponse;
import com.hackathon.inditex.entities.Center;

public interface CenterService {

	DefaultResponse createLogisticsCenter(Center center);
	List<Center> getAllLogisticsCenters();
	void updateLogisticsCenter(Long id, Center center);
	void deleteLogisticsCenter(Long id);
}
