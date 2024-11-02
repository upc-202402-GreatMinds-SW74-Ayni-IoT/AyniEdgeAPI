package com.greatminds.ayni.ayniedgeapi.iot.infrastructure.persistence.jpa.repositories;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.aggregates.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
