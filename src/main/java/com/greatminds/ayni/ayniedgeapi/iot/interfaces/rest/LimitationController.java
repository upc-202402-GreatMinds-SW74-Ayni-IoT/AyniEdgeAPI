package com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest;

import com.greatminds.ayni.ayniedgeapi.iot.domain.model.queries.GetLimitationByCropIdQuery;
import com.greatminds.ayni.ayniedgeapi.iot.domain.model.queries.GetLimitationByIdQuery;
import com.greatminds.ayni.ayniedgeapi.iot.domain.services.LimitationCommandService;
import com.greatminds.ayni.ayniedgeapi.iot.domain.services.LimitationQueryService;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources.CreateLimitationResource;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.resources.LimitationResource;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.transform.CreateLimitationCommandFromResourceAssembler;
import com.greatminds.ayni.ayniedgeapi.iot.interfaces.rest.transform.LimitationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="api/v1/limitations",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Limitations", description = "Limitations Management Endpoints")
public class LimitationController {
    private final LimitationQueryService limitationQueryService;
    private final LimitationCommandService limitationCommandService;

    public LimitationController(LimitationQueryService limitationQueryService, LimitationCommandService limitationCommandService) {
        this.limitationQueryService = limitationQueryService;
        this.limitationCommandService = limitationCommandService;
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

    @GetMapping("/{id}")
    public ResponseEntity<LimitationResource> getLimitation(@PathVariable Long id){
        var getLimitationByIdQuery = new GetLimitationByIdQuery(id);
        var limitation = limitationQueryService.handle(getLimitationByIdQuery);

        if(limitation.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var limitationResource = LimitationResourceFromEntityAssembler.toResourceFromEntity(limitation.get());
        return new ResponseEntity<>(limitationResource, HttpStatus.OK);
    }

    @GetMapping("/{cropId}")
    public ResponseEntity<LimitationResource> getLimitationByCropId(@PathVariable Long cropId){
        var getLimitationByCropIdQuery = new GetLimitationByCropIdQuery(cropId);
        var limitation = limitationQueryService.handle(getLimitationByCropIdQuery);

        if(limitation.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var limitationResource = LimitationResourceFromEntityAssembler.toResourceFromEntity(limitation.get());
        return new ResponseEntity<>(limitationResource, HttpStatus.OK);
    }
}
