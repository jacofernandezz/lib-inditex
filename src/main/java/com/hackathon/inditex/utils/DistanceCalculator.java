package com.hackathon.inditex.utils;

import com.hackathon.inditex.entities.Coordinates;

public class DistanceCalculator {
	
    private DistanceCalculator() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }
	
    public static double calculateHaversineDistance(Coordinates coord1, Coordinates coord2) {
        final int EARTH_RADIUS = 6371;

        double latDistance = Math.toRadians(coord2.getLatitude() - coord1.getLatitude());
        double lonDistance = Math.toRadians(coord2.getLongitude() - coord1.getLongitude());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(coord1.getLatitude())) * Math.cos(Math.toRadians(coord2.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }
}
