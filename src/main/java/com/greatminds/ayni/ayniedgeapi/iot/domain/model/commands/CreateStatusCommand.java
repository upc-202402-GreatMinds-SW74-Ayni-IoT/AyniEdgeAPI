package com.greatminds.ayni.ayniedgeapi.iot.domain.model.commands;

public record CreateStatusCommand(Long cropId, Float temperature, Float humidity, Float oxygen, Float waterLevel) {
}
