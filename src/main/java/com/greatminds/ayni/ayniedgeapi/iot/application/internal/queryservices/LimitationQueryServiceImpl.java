package com.greatminds.ayni.ayniedgeapi.iot.application.internal.queryservices;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.aggregates.Limitation;
import com.greatminds.ayni.ayniedgeapi.iot.domain.model.queries.GetLimitationByCropIdQuery;
import com.greatminds.ayni.ayniedgeapi.iot.domain.model.queries.GetLimitationByIdQuery;
import com.greatminds.ayni.ayniedgeapi.iot.domain.services.LimitationQueryService;
import com.greatminds.ayni.ayniedgeapi.iot.infrastructure.persistence.jpa.repositories.LimitationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LimitationQueryServiceImpl implements LimitationQueryService {
    public final LimitationRepository limitationRepository;

    public LimitationQueryServiceImpl(LimitationRepository limitationRepository) {
        this.limitationRepository = limitationRepository;
    }

    @Override
    public Optional<Limitation> handle(GetLimitationByIdQuery query) {
        return limitationRepository.findById(query.id());
    }

    @Override
    public Optional<Limitation> handle(GetLimitationByCropIdQuery query) {
        return limitationRepository.findByCropId(query.cropId());
    }
}
