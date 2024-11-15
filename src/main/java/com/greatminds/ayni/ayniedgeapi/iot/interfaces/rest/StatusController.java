package com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.aggregates.Status;
import com.greatminds.ayni.ayniedgeapi.iot.domain.model.queries.GetStatusByIdQuery;
import com.greatminds.ayni.ayniedgeapi.iot.domain.services.StatusCommandService;
import com.greatminds.ayni.ayniedgeapi.iot.domain.services.StatusQueryService;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources.CreateStatusResource;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources.StatusResource;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources.UpdateStatusResource;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.transform.CreateStatusCommandFromResourceAssembler;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.transform.StatusResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="api/v1/status", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Status", description = "Status Management Endpoints")
public class StatusController {
    private final StatusQueryService statusQueryService;
    private final StatusCommandService statusCommandService;
    private final RestTemplate restTemplate;

    public StatusController(StatusQueryService statusQueryService, StatusCommandService statusCommandService, RestTemplate restTemplate) {
        this.statusQueryService = statusQueryService;
        this.statusCommandService = statusCommandService;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity<StatusResource> createStatus(@RequestBody CreateStatusResource resource){
        var createStatusCommand = CreateStatusCommandFromResourceAssembler.toCommandFromResource(resource);

        var statusId = statusCommandService.handle(createStatusCommand);
        if(statusId == 0L){
            return ResponseEntity.badRequest().build();
        }

        var getStatusByIdQuery = new GetStatusByIdQuery(statusId);
        var status = statusQueryService.handle(getStatusByIdQuery);

        if(status.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var statusResource = StatusResourceFromEntityAssembler.toResourceFromEntity(status.get());

        sendDataToBackend(status.get());

        return new ResponseEntity<>(statusResource, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusResource> getStatus(@PathVariable Long id){
        var getStatusByIdQuery = new GetStatusByIdQuery(id);
        var status = statusQueryService.handle(getStatusByIdQuery);

        if(status.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var statusResource = StatusResourceFromEntityAssembler.toResourceFromEntity(status.get());

        return new ResponseEntity<>(statusResource, HttpStatus.OK);
    }

    @PutMapping("/{statusId}")
    public ResponseEntity<StatusResource> receiveData(@PathVariable Long statusId ,@RequestBody UpdateStatusResource edgeData) {
        try{
            Long updatedStatusId = statusCommandService.updateStatus(statusId, edgeData);
            Status updatedStatus = statusQueryService.handle(new GetStatusByIdQuery(updatedStatusId))
                    .orElseThrow(() -> new IllegalArgumentException("Status not found"));
            sendDataToBackend(updatedStatus);
            return ResponseEntity.ok().body(StatusResourceFromEntityAssembler.toResourceFromEntity(updatedStatus));
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    private void sendDataToBackend(Status status) {
        Map<String, Object> dataToSend = new HashMap<>();
        dataToSend.put("temperature", status.getTemperature());
        dataToSend.put("hydration", status.getHumidity());
        dataToSend.put("oxygenation", status.getOxygen());
        dataToSend.put("waterLevel", status.getWaterLevel());

        Long cropId = status.getCropId();
        String backendUrl = "https://ayni-v1.sfo1.zeabur.app/api/v1/sensors/" + cropId;

        restTemplate.put(backendUrl, dataToSend);
    }
}
