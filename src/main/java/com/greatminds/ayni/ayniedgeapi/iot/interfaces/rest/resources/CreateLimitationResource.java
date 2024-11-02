package com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources;

public record CreateLimitationResource(Long cropId, double recommendedTemperature, double recommendedHumidity, double recommendedOxygen, double recommendedWater) {
}
