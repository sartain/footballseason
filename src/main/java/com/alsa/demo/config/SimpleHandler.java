package com.alsa.demo.config;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;

public class SimpleHandler implements ObservationHandler<Observation.Context> {

    @Override
    public void onStart(Observation.Context context) {
        System.out.println("START " + "data: " + context.get(String.class));
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
        System.out.println("STOP  " + "data: " + context.get(String.class));
    }

    @Override
    public boolean supportsContext(Observation.Context handlerContext) {
        // you can decide if your handler should be invoked for this context object or
        // not
        return true;
    }

}
