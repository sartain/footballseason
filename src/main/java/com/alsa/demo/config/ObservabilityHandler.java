package com.alsa.demo.config;

import io.micrometer.common.KeyValue;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.stream.StreamSupport;

// Example of plugging in a custom handler that in this case will print a statement before and after all observations take place
@Component
class ObservabilityHandler implements ObservationHandler<Observation.Context> {

    private MeterRegistry registry = new SimpleMeterRegistry();
    private Timer.Sample sample;
    private static final Logger log = LoggerFactory.getLogger(ObservabilityHandler.class);

    /*
    @Override
    public void onStart(Observation.Context context) {
        log.info(context.toString());
        log.info("Before running the observation for context [{}], leagueName [{}]", context.getName(), getLeagueNameFromContext(context));
    }

    @Override
    public void onStop(Observation.Context context) {
        log.info("After running the observation for context [{}], leagueName [{}]", context.getName(), getLeagueNameFromContext(context));
    }

    @Override
    public boolean supportsContext(Observation.Context context) {
        return true;
    }

    private String getLeagueNameFromContext(Observation.Context context) {
        return StreamSupport.stream(context.getLowCardinalityKeyValues().spliterator(), false)
                .filter(keyValue -> "leagueName".equals(keyValue.getKey()))
                .map(KeyValue::getValue)
                .findFirst()
                .orElse("UNKNOWN");
    }
     */
    @Override
    public void onStart(Observation.Context context) {
        sample = Timer.start(registry);
        System.out.println("START " + "data: " + context.getName());
    }

    @Override
    public void onError(Observation.Context context) {
        System.out.println("ERROR " + "data: " + context.get(String.class) + ", error: " + context.getError());
    }

    @Override
    public void onEvent(Observation.Event event, Observation.Context context) {
        System.out.println("EVENT " + "event: " + event + " data: " + context.get(String.class));
    }

    @Override
    public void onStop(Observation.Context context) {
        long time = sample.stop(Timer.builder("my.timer").register(registry));
        System.out.println("STOP  " + "data: " + context.get(String.class) + " time: " + Duration.ofNanos(time).toMillis() + "ms");
    }

    @Override
    public boolean supportsContext(Observation.Context handlerContext) {
        return true;
        // you can decide if your handler should be invoked for this context object or
        // not
    }
}
