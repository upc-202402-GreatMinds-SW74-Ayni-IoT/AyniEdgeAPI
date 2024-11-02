package com.greatminds.ayni.ayniedgeapi.iot.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @Getter
    private Long id;

    @Getter
    private Long cropId;

    @Getter
    private double temperature;

    @Getter
    private double humidity;

    @Getter
    private double oxygen;

    @Getter
    private double waterLevel;

    public Status() {}

    public Status(Long cropId, double temperature, double humidity, double oxygen, double waterLevel) {
        this.cropId = cropId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.oxygen = oxygen;
        this.waterLevel = waterLevel;
    }
}
