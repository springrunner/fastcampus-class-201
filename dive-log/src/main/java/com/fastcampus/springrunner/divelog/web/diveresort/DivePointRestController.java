package com.fastcampus.springrunner.divelog.web.diveresort;

import org.springframework.web.bind.annotation.RestController;

import com.fastcampus.springrunner.divelog.core.diveresort.application.DivePointEditor;
import com.fastcampus.springrunner.divelog.core.diveresort.application.DivePointFinder;

@RestController
public class DivePointRestController {
    private final DivePointFinder divePointFinder;
    private final DivePointEditor divePointEditor;

    public DivePointRestController(DivePointFinder divePointFinder, DivePointEditor divePointEditor) {
        this.divePointFinder = divePointFinder;
        this.divePointEditor = divePointEditor;
    }

}
