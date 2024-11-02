package com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources;

public record CreateStatusResource(Long CropId, double temperature, double humidity, double oxygen, double water) {
}
