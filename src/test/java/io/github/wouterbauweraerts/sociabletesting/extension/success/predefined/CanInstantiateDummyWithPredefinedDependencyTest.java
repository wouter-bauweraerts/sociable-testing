package io.github.wouterbauweraerts.sociabletesting.extension.success.predefined;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

import org.junit.jupiter.api.Test;

import io.github.wouterbauweraerts.sociabletesting.annotation.Predefined;
import io.github.wouterbauweraerts.sociabletesting.annotation.SociableTest;
import io.github.wouterbauweraerts.sociabletesting.annotation.TestSubject;

@SociableTest
class CanInstantiateDummyWithPredefinedDependencyTest {
    private static final Random RANDOM = new Random();

    @TestSubject
    DummyWithPredefinedDependency subject;

    @Predefined
    PredefinedService predefinedDependency = new PredefinedService(RANDOM.nextInt(), "Random String %d".formatted(RANDOM.nextInt()));

    @Test
    void createsExpected() {
        assertThat(subject).isNotNull();

        assertThat(subject.service()).isNotNull()
                .isSameAs(predefinedDependency);
    }

    public record DummyWithPredefinedDependency(PredefinedService service) {
    }

    public record PredefinedService(int intValue, String stringValue) {
    }
}
