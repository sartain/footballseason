package com.alsa.demo.config;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Configuration(proxyBeanMethods = false)
class ObserveConfig {
/*
    private static final Logger log = LoggerFactory.getLogger(ObserveConfig.class);

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    CommandLineRunner myCommandLineRunner(ObservationRegistry registry, RestTemplate restTemplate) {
        List<String> lowCardinalityValues = Arrays.asList("Premier League", "Fake League"); // Simulates low number of values
        return args -> {
            String highCardinalityLeague = "Premier League";
            Observation.createNotStarted("my.observation", registry)
                    // Low cardinality means that the number of potential values won't be big. Low cardinality entries will end up in e.g. Metrics
                    .lowCardinalityKeyValue("leagueName", randomLeaguePicker(lowCardinalityValues))
                    // High cardinality means that the number of potential values can be large. High cardinality entries will end up in e.g. Spans
                    .highCardinalityKeyValue("highCardinalityKey", "")
                    // <command-line-runner> is a "contextual" name that gives more details within the provided context. It will be used to name e.g. Spans
                    .contextualName("command-line-runner")
                    // The following lambda will be executed with an observation scope (e.g. all the MDC entries will be populated with tracing information). Also the observation will be started, stopped and if an error occurred it will be recorded on the observation
                    .observe(() -> {
                        log.info("Will send a request to the server"); // Since we're in an observation scope - this log line will contain tracing MDC entries ...
                        String response = restTemplate.getForObject("http://localhost:7654/leagueposition/{leagueName}", String.class, highCardinalityLeague); // Boot's RestTemplate instrumentation creates a child span here
                        log.info("Got response [{}]", response); // ... so will this line
                    });

        };
    }

    private String randomLeaguePicker(List<String> lowCardinalityValues) {
        Random highCardinalityValues = new Random(); // Simulates potentially large number of values
        return lowCardinalityValues.get(highCardinalityValues.nextInt(0,1));

    }
 */
}