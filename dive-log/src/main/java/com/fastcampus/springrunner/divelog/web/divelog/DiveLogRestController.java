package com.fastcampus.springrunner.divelog.web.divelog;

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

import com.fastcampus.springrunner.divelog.core.divelog.application.DiveLogEditor;
import com.fastcampus.springrunner.divelog.core.divelog.application.DiveLogFinder;
import com.fastcampus.springrunner.divelog.core.divelog.application.dto.DiveLogDto;
import com.fastcampus.springrunner.divelog.web.divelog.dto.DiveLogRegisterRequest;
import com.fastcampus.springrunner.divelog.web.divelog.dto.DiveLogUpdateRequest;

@RestController
public class DiveLogRestController {
    private final DiveLogFinder diveLogFinder;
    private final DiveLogEditor diveLogEditor;

    public DiveLogRestController(DiveLogFinder diveLogFinder, DiveLogEditor diveLogEditor) {
        this.diveLogFinder = diveLogFinder;
        this.diveLogEditor = diveLogEditor;
    }

    @GetMapping("/dive-logs")
    public ResponseEntity<List<DiveLogDto>> getAll() {
        return ResponseEntity.ok(diveLogFinder.findAll());
    }

    @PostMapping("/dive-logs")
    public ResponseEntity<?> register(HttpServletRequest servletRequest,
            @RequestBody @Validated DiveLogRegisterRequest registerREquest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMaps = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.badRequest().body(errorMaps);
        }

        DiveLogDto result = diveLogEditor.save(registerREquest.convertToRegisterCommand());

        String location = ServletUriComponentsBuilder.fromContextPath(servletRequest).path("/dive-logs/{id}")
                .buildAndExpand(result.getId()).toString();

        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, location).body(result);
    }

    @GetMapping("/dive-logs/{diveLogId}")
    public ResponseEntity<DiveLogDto> findById(@PathVariable("diveLogId") Long diveLogId) {
        return ResponseEntity.of(diveLogFinder.findById(diveLogId));
    }

    @PutMapping("/dive-logs/{diveLogId}")
    public ResponseEntity<?> update(@PathVariable("diveLogId") Long diveLogId,
            @RequestBody @Validated DiveLogUpdateRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMaps = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.badRequest().body(errorMaps);
        }

        return ResponseEntity.ok(diveLogEditor.update(diveLogId, request.convertToUpdateCommand()));
    }

    @DeleteMapping("/dive-logs/{diveLogId}")
    public ResponseEntity<Void> delete(@PathVariable("diveLogId") Long diveLogId) {
        diveLogEditor.delete(diveLogId);
        return ResponseEntity.noContent().build();
    }
}
