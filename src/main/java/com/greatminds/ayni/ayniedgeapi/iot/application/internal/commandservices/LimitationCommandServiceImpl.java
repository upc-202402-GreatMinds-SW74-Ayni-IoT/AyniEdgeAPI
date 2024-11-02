package com.greatminds.ayni.ayniedgeapi.iot.application.internal.commandservices;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.aggregates.Limitation;
import com.greatminds.ayni.ayniedgeapi.iot.domain.model.commands.CreateLimitationCommand;
import com.greatminds.ayni.ayniedgeapi.iot.domain.services.LimitationCommandService;
import com.greatminds.ayni.ayniedgeapi.iot.infrastructure.persistence.jpa.repositories.LimitationRepository;
import org.springframework.stereotype.Service;

@Service
public class LimitationCommandServiceImpl implements LimitationCommandService {
    private final LimitationRepository limitationRepository;

    public LimitationCommandServiceImpl(LimitationRepository limitationRepository) {
        this.limitationRepository = limitationRepository;
    }

    @Override
    public Long handle(CreateLimitationCommand command) {
        var limitation = new Limitation(command.cropId(), command.recommendedTemperature(), command.recommendedHumidity(), command.recommendedOxygen(), command.recommendedWaterLevel());
        limitationRepository.save(limitation);
        return limitation.getId();
    }
}
