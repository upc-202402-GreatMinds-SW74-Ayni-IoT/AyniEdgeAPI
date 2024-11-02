package com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.transform;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.aggregates.Status;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources.StatusResource;

public class StatusResourceFromEntityAssembler {
    public static StatusResource toResourceFromEntity(Status entity) {
        return new StatusResource(
                entity.getId(),
                entity.getCropId(),
                entity.getTemperature(),
                entity.getHumidity(),
                entity.getOxygen(),
                entity.getWaterLevel()
        );
    }
}
