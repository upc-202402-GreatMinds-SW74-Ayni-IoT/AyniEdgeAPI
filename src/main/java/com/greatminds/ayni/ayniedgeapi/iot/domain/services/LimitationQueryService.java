package com.greatminds.ayni.ayniedgeapi.iot.domain.services;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.aggregates.Limitation;
import com.greatminds.ayni.ayniedgeapi.iot.domain.model.queries.GetLimitationByCropIdQuery;
import com.greatminds.ayni.ayniedgeapi.iot.domain.model.queries.GetLimitationByIdQuery;

import java.util.Optional;

public interface LimitationQueryService {
    Optional<Limitation> handle(GetLimitationByIdQuery query);
    Optional<Limitation> handle(GetLimitationByCropIdQuery query);
}
