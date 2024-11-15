package com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources;

public record UpdateStatusResource(Long cropId, Float temperature, Float humidity, Float oxygen, Float waterLevel) {
}
