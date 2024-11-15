package com.greatminds.ayni.ayniedgeapi.iot.domain.services;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.commands.CreateLimitationCommand;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources.UpdateLimitationResource;

public interface LimitationCommandService {
    Long handle(CreateLimitationCommand command);
    Long updateLimitation(Long cropId, UpdateLimitationResource request);
}
