package com.greatminds.ayni.ayniedgeapi.iot.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private Long cropId;

    @Getter
    private Float temperature;

    @Getter
    private Float humidity;

    @Getter
    private Float oxygen;

    @Getter
    private Float waterLevel;

    public Status() {}

    public Status(Long cropId, Float temperature, Float humidity, Float oxygen, Float waterLevel) {
        this.cropId = cropId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.oxygen = oxygen;
        this.waterLevel = waterLevel;
    }

    public void update(Status request) {
        if (request.temperature != null) {
            this.temperature = request.temperature;
        }
        if (request.humidity != null) {
            this.humidity = request.humidity;
        }
        if (request.oxygen != null) {
            this.oxygen = request.oxygen;
        }
        if (request.waterLevel != null) {
            this.waterLevel = request.waterLevel;
        }
        if (request.cropId != null) {
            this.cropId = request.cropId;
        }
    }
}
