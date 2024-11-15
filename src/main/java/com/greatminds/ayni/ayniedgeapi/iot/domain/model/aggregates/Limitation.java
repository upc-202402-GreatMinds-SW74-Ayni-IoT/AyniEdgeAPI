package com.greatminds.ayni.ayniedgeapi.iot.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "limitation")
public class Limitation {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private Long cropId;

    @Getter
    private Float recommendedTemperature;

    @Getter
    private Float recommendedHumidity;

    @Getter
    private Float recommendedOxygen;

    @Getter
    private Float recommendedWaterLevel;

    public Limitation() {}

    public Limitation(Long cropId, Float recommendedTemperature, Float recommendedHumidity, Float recommendedOxygen, Float recommendedWaterLevel) {
        this.cropId = cropId;
        this.recommendedTemperature = recommendedTemperature;
        this.recommendedHumidity = recommendedHumidity;
        this.recommendedOxygen = recommendedOxygen;
        this.recommendedWaterLevel = recommendedWaterLevel;
    }

    public void update(Limitation request) {
        if (request.cropId != null) {
            this.cropId = request.cropId;
        }
        if (request.recommendedTemperature != null) {
            this.recommendedTemperature = request.recommendedTemperature;
        }
        if (request.recommendedHumidity != null) {
            this.recommendedHumidity = request.recommendedHumidity;
        }
        if (request.recommendedOxygen != null) {
            this.recommendedOxygen = request.recommendedOxygen;
        }
        if (request.recommendedWaterLevel != null) {
            this.recommendedWaterLevel = request.recommendedWaterLevel;
        }
    }
}
