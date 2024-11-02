package com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources;

public record LimitationResource(Long id, Long cropId, Float recommendedTemperature, Float recommendedHumidity, Float recommendedOxygen, Float recommendedWaterLevel) {
}
