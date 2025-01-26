package com.hackathon.inditex.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hackathon.inditex.entities.Center;
import com.hackathon.inditex.entities.Coordinates;
import com.hackathon.inditex.enums.CenterStatus;

public interface CenterRepository extends JpaRepository<Center, Long> {
    Optional<Center> findByCoordinates(Coordinates coordinates);
    List<Center> findByStatus(CenterStatus status);
    @Query(value = "SELECT " +
            "CASE " +
            "    WHEN COUNT(c.id) = 0 THEN 0 " + 
            "    WHEN :currentLoad > c.max_capacity THEN 2 " +
            "    ELSE 1 " + 
            "END AS result " +
            "FROM centers c " +
            "WHERE c.id = :id", nativeQuery = true)
    Integer validateCenterAndLoad(@Param("id") Long id, @Param("currentLoad") Integer currentLoad);
}
