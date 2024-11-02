package com.greatminds.ayni.ayniedgeapi.iot.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "limitation")
public class Limitation {
    @Id
    @Getter
    private Long id;

    @Getter
    private Long cropId;

    @Getter
    private double recommendedTemperature;

    @Getter
    private double recommendedHumidity;

    @Getter
    private double recommendedOxygen;

    @Getter
    private double recommendedWaterLevel;

    public Limitation() {}

    public Limitation(Long cropId, double recommendedTemperature, double recommendedHumidity, double recommendedOxygen, double recommendedWaterLevel) {
        this.cropId = cropId;
        this.recommendedTemperature = recommendedTemperature;
        this.recommendedHumidity = recommendedHumidity;
        this.recommendedOxygen = recommendedOxygen;
        this.recommendedWaterLevel = recommendedWaterLevel;
    }
}
