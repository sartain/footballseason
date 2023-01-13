package com.alsa.demo.observation;

import com.alsa.demo.config.Example;
import io.micrometer.observation.tck.TestObservationRegistry;
import io.micrometer.observation.tck.TestObservationRegistryAssert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ObservationTests {

    @Test
    void should_assert_your_observation() {

        // create a test registry in your tests
        TestObservationRegistry registry = TestObservationRegistry.create();

        // run your production code with the TestObservationRegistry
        new Example(registry).run();

        // check your observation
        TestObservationRegistryAssert.assertThat(registry)
                .doesNotHaveAnyRemainingCurrentObservation()
                .hasObservationWithNameEqualTo("get-league-position")
                .that()
                .hasBeenStarted()
                .hasBeenStopped();
    }

}
