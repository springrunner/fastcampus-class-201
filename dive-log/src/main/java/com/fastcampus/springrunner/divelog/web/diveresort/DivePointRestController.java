package com.fastcampus.springrunner.divelog.web.diveresort;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fastcampus.springrunner.divelog.core.diveresort.application.DivePointEditor;
import com.fastcampus.springrunner.divelog.core.diveresort.application.DivePointFinder;
import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DivePointDto;
import com.fastcampus.springrunner.divelog.web.diveresort.dto.DivePointRegisterRequest;
import com.fastcampus.springrunner.divelog.web.diveresort.dto.DivePointUpdateRequest;

@RestController
public class DivePointRestController {
    private final DivePointFinder divePointFinder;
    private final DivePointEditor divePointEditor;

    public DivePointRestController(DivePointFinder divePointFinder, DivePointEditor divePointEditor) {
        this.divePointFinder = divePointFinder;
        this.divePointEditor = divePointEditor;
    }

    @GetMapping("/dive-points")
    public ResponseEntity<List<DivePointDto>> getDivePoints() {
        return ResponseEntity.ok(divePointFinder.findAll());
    }

    @PostMapping("/dive-points")
    public ResponseEntity<?> save(HttpServletRequest servletRequest,
            @RequestBody @Validated DivePointRegisterRequest request, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMaps = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.badRequest().body(errorMaps);
        }
        
        DivePointDto result = divePointEditor.save(request.convertToRegisterCommand());

        String location = ServletUriComponentsBuilder.fromContextPath(servletRequest).path("/dive-points/{id}")
                .buildAndExpand(result.getId()).toString();

        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, location).body(result);
    }

    @GetMapping("/dive-points/{divePointId}")
    public ResponseEntity<DivePointDto> findById(@PathVariable("divePointId") Long divePointId) {
        return ResponseEntity.of(divePointFinder.findByDivePointId(divePointId));
    }

    @PutMapping("/dive-points/{divePointId}")
    public ResponseEntity<?> update(@PathVariable("divePointId") Long divePointId,
            @RequestBody @Validated DivePointUpdateRequest updateRequest, BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMaps = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.badRequest().body(errorMaps);
        }
        
        return ResponseEntity.ok(divePointEditor.update(divePointId, updateRequest.convertToUpdateCommand()));
    }

    @DeleteMapping("/dive-points/{divePointId}")
    public ResponseEntity<Void> delete(@PathVariable("divePointId") Long divePointId) {
        divePointEditor.delete(divePointId);
        return ResponseEntity.noContent().build();
    }
}
