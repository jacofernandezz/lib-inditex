package com.hackathon.inditex.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Coordinates {
	@Column(name = "latitude")
    private Double latitude;
	@Column(name = "longitude")
    private Double longitude;    
}
