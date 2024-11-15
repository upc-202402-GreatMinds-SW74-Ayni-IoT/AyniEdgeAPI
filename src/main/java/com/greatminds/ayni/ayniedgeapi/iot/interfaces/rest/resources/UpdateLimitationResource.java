package com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources;

public record UpdateLimitationResource(Long cropId, Float recommendedTemperature, Float recommendedHumidity, Float recommendedOxygen, Float recommendedWaterlevel) {
}
