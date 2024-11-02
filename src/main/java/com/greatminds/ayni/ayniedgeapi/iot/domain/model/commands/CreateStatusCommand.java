package com.greatminds.ayni.ayniedgeapi.iot.domain.model.commands;

public record CreateStatusCommand(Long cropId, double temperature, double humidity, double oxygen, double waterLevel) {
}
