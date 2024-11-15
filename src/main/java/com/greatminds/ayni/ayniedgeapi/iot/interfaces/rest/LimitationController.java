package com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.aggregates.Limitation;
import com.greatminds.ayni.ayniedgeapi.iot.domain.model.queries.GetLimitationByCropIdQuery;
import com.greatminds.ayni.ayniedgeapi.iot.domain.model.queries.GetLimitationByIdQuery;
import com.greatminds.ayni.ayniedgeapi.iot.domain.services.LimitationCommandService;
import com.greatminds.ayni.ayniedgeapi.iot.domain.services.LimitationQueryService;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources.CreateLimitationResource;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources.LimitationResource;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources.UpdateLimitationResource;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.transform.CreateLimitationCommandFromResourceAssembler;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.transform.LimitationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="api/v1/limitations",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Limitations", description = "Limitations Management Endpoints")
public class LimitationController {
    private final LimitationQueryService limitationQueryService;
    private final LimitationCommandService limitationCommandService;
    private final RestTemplate restTemplate;

    public LimitationController(LimitationQueryService limitationQueryService, LimitationCommandService limitationCommandService, RestTemplate restTemplate) {
        this.limitationQueryService = limitationQueryService;
        this.limitationCommandService = limitationCommandService;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity<LimitationResource> createLimitation(@RequestBody CreateLimitationResource resource){
        var createLimitationCommand = CreateLimitationCommandFromResourceAssembler.toCommandFromResource(resource);

        var limitationId = limitationCommandService.handle(createLimitationCommand);
        if(limitationId == 0L){
            return ResponseEntity.badRequest().build();
        }

        var getLimitationByIdQuery = new GetLimitationByIdQuery(limitationId);
        var limitation = limitationQueryService.handle(getLimitationByIdQuery);

        if(limitation.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var limitationResource = LimitationResourceFromEntityAssembler.toResourceFromEntity(limitation.get());
        return new ResponseEntity<>(limitationResource, HttpStatus.CREATED);
    }

    @PutMapping("/{limitationId}")
    public ResponseEntity<LimitationResource> updateLimitation(
            @PathVariable Long limitationId) {
        try {
            Map<String, Object> cropData = fetchCropData(limitationId);

            if (cropData != null) {
                UpdateLimitationResource resource = new UpdateLimitationResource(
                        limitationId,
                        cropData.get("recommendedTemperature") != null ? ((Double) cropData.get("recommendedTemperature")).floatValue() : null,
                        cropData.get("recommendedHumidity") != null ? ((Double) cropData.get("recommendedHumidity")).floatValue() : null,
                        cropData.get("recommendedOxygen") != null ? ((Double) cropData.get("recommendedOxygen")).floatValue() : null,
                        cropData.get("recommendedWaterLevel") != null ? ((Double) cropData.get("recommendedWaterLevel")).floatValue() : null
                );

                Long updatedLimitationId = limitationCommandService.updateLimitation(limitationId, resource);
                Limitation updatedLimitation = limitationQueryService.handle(new GetLimitationByIdQuery(updatedLimitationId))
                        .orElseThrow(() -> new IllegalArgumentException("Limitation does not exist"));

                return new ResponseEntity<>(LimitationResourceFromEntityAssembler.toResourceFromEntity(updatedLimitation), HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<LimitationResource> getLimitation(@PathVariable Long id){
        var getLimitationByIdQuery = new GetLimitationByIdQuery(id);
        var limitation = limitationQueryService.handle(getLimitationByIdQuery);

        if(limitation.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var limitationResource = LimitationResourceFromEntityAssembler.toResourceFromEntity(limitation.get());
        return new ResponseEntity<>(limitationResource, HttpStatus.OK);
    }

    @GetMapping("/by-crop-id/{cropId}")
    public ResponseEntity<LimitationResource> getLimitationByCropId(@PathVariable Long cropId){
        var getLimitationByCropIdQuery = new GetLimitationByCropIdQuery(cropId);
        var limitation = limitationQueryService.handle(getLimitationByCropIdQuery);

        if(limitation.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var limitationResource = LimitationResourceFromEntityAssembler.toResourceFromEntity(limitation.get());
        return new ResponseEntity<>(limitationResource, HttpStatus.OK);
    }

    private Map<String, Object> fetchCropData(Long cropId) {
        try {
            String cropEndpoint = "https://ayni-v1.sfo1.zeabur.app/api/v1/crops/" + cropId;
            ResponseEntity<Map> response = restTemplate.getForEntity(cropEndpoint, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
