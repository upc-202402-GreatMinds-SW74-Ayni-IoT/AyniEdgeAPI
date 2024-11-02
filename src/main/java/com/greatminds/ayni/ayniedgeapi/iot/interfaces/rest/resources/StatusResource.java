package com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources;

public record StatusResource(Long id, Long cropId, double temperature, double humidity, double oxygen, double water) {
}
