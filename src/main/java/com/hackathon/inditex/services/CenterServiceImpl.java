package com.hackathon.inditex.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.inditex.dtos.DefaultResponse;
import com.hackathon.inditex.entities.Center;
import com.hackathon.inditex.exceptions.InvalidCapacityException;
import com.hackathon.inditex.exceptions.ResourceNotFoundException;
import com.hackathon.inditex.repositories.CenterRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CenterServiceImpl implements CenterService{
	
	private final CenterRepository repository;

	@Override
	@Transactional
	public DefaultResponse createLogisticsCenter(Center center) {
        validateCenter(center);

        repository.save(center);
        return new DefaultResponse("Logistics center created successfully.");
	}

	private void validateCenter(Center center) {
	    repository.findByCoordinates(center.getCoordinates())
        .ifPresent(existingCenter -> {
            throw new InvalidCapacityException("There is already a logistics center in that position.");
        });
	    validateCapacity(center,center);
	}

	@Override
    public List<Center> getAllLogisticsCenters() {
        return repository.findAll();
    }

	@Override
	@Transactional
	public void updateLogisticsCenter(Long id, Center updateRequest) {
	    Map<Integer, Runnable> exceptionMap = Map.of(
	            0, () -> { throw new ResourceNotFoundException("Center not found."); },
	            2, () -> { throw new InvalidCapacityException("Current load cannot exceed max capacity."); }
	        );

	    Integer result = repository.validateCenterAndLoad(id, updateRequest.getCurrentLoad());

	    Optional.ofNullable(exceptionMap.get(result))
	            .ifPresent(Runnable::run);
		//Center center = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Center not found."));
		Center center = repository.getReferenceById(id);
//		validateCapacity(updateRequest, center);
		Optional.ofNullable(updateRequest.getStatus()).ifPresent(center::setStatus);
		Optional.ofNullable(updateRequest.getCurrentLoad()).ifPresent(center::setCurrentLoad);
		repository.save(center);
	}

	private void validateCapacity(Center updateRequest, Center center) {
		Optional.ofNullable(updateRequest.getCurrentLoad())
	        .filter(currentLoad -> currentLoad > center.getMaxCapacity())
	        .ifPresent(dto -> {
	            throw new InvalidCapacityException("Current load cannot exceed max capacity.");
	        });
	}

    @Transactional
    public void deleteLogisticsCenter(Long id) {
        repository.deleteById(id);
    }
    
}
