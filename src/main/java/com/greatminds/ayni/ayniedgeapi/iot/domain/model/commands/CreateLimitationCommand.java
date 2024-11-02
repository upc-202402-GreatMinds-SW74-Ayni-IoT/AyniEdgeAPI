package com.greatminds.ayni.ayniedgeapi.iot.domain.model.commands;

public record CreateLimitationCommand(Long cropId, double recommendedTemperature, double recommendedHumidity, double recommendedOxygen, double recommendedWaterLevel) {
}
