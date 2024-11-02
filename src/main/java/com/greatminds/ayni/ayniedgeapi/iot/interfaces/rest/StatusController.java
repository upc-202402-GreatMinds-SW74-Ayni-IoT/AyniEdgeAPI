package com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.queries.GetStatusByIdQuery;
import com.greatminds.ayni.ayniedgeapi.iot.domain.services.StatusCommandService;
import com.greatminds.ayni.ayniedgeapi.iot.domain.services.StatusQueryService;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources.CreateStatusResource;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources.StatusResource;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.transform.CreateStatusCommandFromResourceAssembler;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.transform.StatusResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="api/v1/status", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Status", description = "Status Management Endpoints")
public class StatusController {
    private final StatusQueryService statusQueryService;
    private final StatusCommandService statusCommandService;

    public StatusController(StatusQueryService statusQueryService, StatusCommandService statusCommandService) {
        this.statusQueryService = statusQueryService;
        this.statusCommandService = statusCommandService;
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
}
