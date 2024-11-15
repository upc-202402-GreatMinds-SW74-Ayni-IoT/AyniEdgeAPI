package com.greatminds.ayni.ayniedgeapi.iot.domain.services;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.commands.CreateStatusCommand;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources.UpdateStatusResource;

public interface StatusCommandService {
    Long handle(CreateStatusCommand command);
    Long updateStatus(Long cropId, UpdateStatusResource request);
}
