package com.greatminds.ayni.ayniedgeapi.iot.application.internal.commandservices;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.aggregates.Status;
import com.greatminds.ayni.ayniedgeapi.iot.domain.model.commands.CreateStatusCommand;
import com.greatminds.ayni.ayniedgeapi.iot.domain.services.StatusCommandService;
import com.greatminds.ayni.ayniedgeapi.iot.infrastructure.persistence.jpa.repositories.StatusRepository;
import org.springframework.stereotype.Service;

@Service
public class StatusCommandServiceImpl implements StatusCommandService {
    private final StatusRepository statusRepository;

    public StatusCommandServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Long handle(CreateStatusCommand command) {
        var status = new Status(command.cropId(), command.temperature(), command.humidity(), command.oxygen(), command.waterLevel());
        statusRepository.save(status);
        return status.getId();
    }
}
