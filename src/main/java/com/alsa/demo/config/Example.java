package com.alsa.demo.config;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;

public class Example {

    private final ObservationRegistry registry;

    public Example(ObservationRegistry registry) {
        this.registry = registry;
    }

    public void run() {
        Observation.createNotStarted("get-league-position", registry)
                .lowCardinalityKeyValue("lowTag", "lowTagValue")
                .highCardinalityKeyValue("highTag", "highTagValue")
                .observe(() -> System.out.println("Hello"));
    }

}
