package com.greatminds.ayni.ayniedgeapi.iot.application.internal.queryservices;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.aggregates.Status;
import com.greatminds.ayni.ayniedgeapi.iot.domain.model.queries.GetStatusByIdQuery;
import com.greatminds.ayni.ayniedgeapi.iot.domain.services.StatusQueryService;
import com.greatminds.ayni.ayniedgeapi.iot.infrastructure.persistence.jpa.repositories.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusQueryServiceImpl implements StatusQueryService {
    public final StatusRepository statusRepository;

    public StatusQueryServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Optional<Status> handle(GetStatusByIdQuery query) {
        return statusRepository.findById(query.id());
    }
}
