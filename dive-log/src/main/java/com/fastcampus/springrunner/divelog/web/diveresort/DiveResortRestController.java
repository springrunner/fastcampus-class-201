package com.fastcampus.springrunner.divelog.web.diveresort;

import org.springframework.web.bind.annotation.RestController;

import com.fastcampus.springrunner.divelog.core.diveresort.application.DiveResortEditor;
import com.fastcampus.springrunner.divelog.core.diveresort.application.DiveResortFinder;

@RestController
public class DiveResortRestController {
    private final DiveResortFinder diveResortFinder;
    private final DiveResortEditor diveResortEditor;

    public DiveResortRestController(DiveResortFinder diveResortFinder, DiveResortEditor diveResortEditor) {
        this.diveResortFinder = diveResortFinder;
        this.diveResortEditor = diveResortEditor;
    }

}
