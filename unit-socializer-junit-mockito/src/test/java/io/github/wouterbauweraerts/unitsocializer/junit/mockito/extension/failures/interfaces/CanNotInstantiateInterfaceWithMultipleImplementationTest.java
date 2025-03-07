package io.github.wouterbauweraerts.unitsocializer.junit.mockito.extension.failures.interfaces;

import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import io.github.wouterbauweraerts.unitsocializer.core.annotations.TestSubject;
import io.github.wouterbauweraerts.unitsocializer.junit.mockito.annotations.SociableTest;

@SociableTest
@Tag("dummyTest")
public class CanNotInstantiateInterfaceWithMultipleImplementationTest {
    @TestSubject
    DependsOnDummyWithMultipleImplementations subject;

    @Test
    void shouldFail() {
        fail("Did not expect to start test");
    }
}
