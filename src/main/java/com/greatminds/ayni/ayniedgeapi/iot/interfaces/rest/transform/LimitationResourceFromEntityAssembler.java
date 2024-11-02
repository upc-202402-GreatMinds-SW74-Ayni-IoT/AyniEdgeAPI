package com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.transform;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.aggregates.Limitation;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources.LimitationResource;

public class LimitationResourceFromEntityAssembler {
    public static LimitationResource toResourceFromEntity(Limitation entity) {
        return new LimitationResource(
                entity.getId(),
                entity.getCropId(),
                entity.getRecommendedTemperature(),
                entity.getRecommendedHumidity(),
                entity.getRecommendedOxygen(),
                entity.getRecommendedWaterLevel()
        );
    }
}
