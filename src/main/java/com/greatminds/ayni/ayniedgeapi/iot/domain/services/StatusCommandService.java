package com.greatminds.ayni.ayniedgeapi.iot.domain.services;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.commands.CreateStatusCommand;

public interface StatusCommandService {
    Long handle(CreateStatusCommand command);
}
