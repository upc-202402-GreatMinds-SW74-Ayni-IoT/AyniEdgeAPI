package com.greatminds.ayni.ayniedgeapi.iot.domain.services;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.commands.CreateLimitationCommand;

public interface LimitationCommandService {
    Long handle(CreateLimitationCommand command);
}
