package com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.transform;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.commands.CreateStatusCommand;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources.CreateStatusResource;

public class CreateStatusCommandFromResourceAssembler {
    public static CreateStatusCommand toCommandFromResource(CreateStatusResource resource) {
        return new CreateStatusCommand(resource.CropId(), resource.temperature(), resource.humidity(), resource.oxygen(), resource.water());
    }
}
