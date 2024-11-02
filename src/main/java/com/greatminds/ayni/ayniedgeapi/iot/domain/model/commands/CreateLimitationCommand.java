package com.greatminds.ayni.ayniedgeapi.iot.domain.model.commands;

public record CreateLimitationCommand(Long cropId, Float recommendedTemperature, Float recommendedHumidity, Float recommendedOxygen, Float recommendedWaterLevel) {
}
