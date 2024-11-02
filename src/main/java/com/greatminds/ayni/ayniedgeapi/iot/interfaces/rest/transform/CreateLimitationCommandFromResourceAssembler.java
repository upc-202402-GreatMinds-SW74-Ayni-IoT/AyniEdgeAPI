package com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.transform;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.commands.CreateLimitationCommand;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources.CreateLimitationResource;

public class CreateLimitationCommandFromResourceAssembler {
    public static CreateLimitationCommand toCommandFromResource(CreateLimitationResource resource) {
        return new CreateLimitationCommand(resource.cropId(), resource.recommendedTemperature(), resource.recommendedHumidity(), resource.recommendedOxygen(), resource.recommendedWaterlevel());
    }
}
