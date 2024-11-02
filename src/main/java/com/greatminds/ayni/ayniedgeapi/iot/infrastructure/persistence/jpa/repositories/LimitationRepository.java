package com.greatminds.ayni.ayniedgeapi.iot.infrastructure.persistence.jpa.repositories;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.aggregates.Limitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LimitationRepository extends JpaRepository<Limitation, Long> {
    Optional<Limitation> findByCropId(Long cropId);
}
