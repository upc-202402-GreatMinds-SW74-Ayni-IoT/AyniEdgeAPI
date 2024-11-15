package com.greatminds.ayni.ayniedgeapi.iot.application.internal.commandservices;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.aggregates.Limitation;
import com.greatminds.ayni.ayniedgeapi.iot.domain.model.commands.CreateLimitationCommand;
import com.greatminds.ayni.ayniedgeapi.iot.domain.services.LimitationCommandService;
import com.greatminds.ayni.ayniedgeapi.iot.infrastructure.persistence.jpa.repositories.LimitationRepository;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources.UpdateLimitationResource;
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

    @Override
    public Long updateLimitation(Long cropId, UpdateLimitationResource request) {
        Limitation limitation = limitationRepository.findById(cropId)
                .orElseThrow(() -> new IllegalArgumentException("Limitation does not exist"));

        limitation.update(new Limitation(request.cropId(), request.recommendedTemperature(), request.recommendedHumidity(), request.recommendedOxygen(), request.recommendedWaterlevel()));
        limitationRepository.save(limitation);
        return limitation.getId();
    }
}
