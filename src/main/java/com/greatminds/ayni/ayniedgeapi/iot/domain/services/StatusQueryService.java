package com.greatminds.ayni.ayniedgeapi.iot.domain.services;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.aggregates.Status;
import com.greatminds.ayni.ayniedgeapi.iot.domain.model.queries.GetStatusByIdQuery;

import java.util.Optional;

public interface StatusQueryService {
    Optional<Status> handle(GetStatusByIdQuery query);
}
