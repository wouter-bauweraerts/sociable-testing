package io.github.wouterbauweraerts.sociabletesting.junit.mockito.extension.failures.abstractclasses;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import io.github.wouterbauweraerts.sociabletesting.core.annotations.TestSubject;
import io.github.wouterbauweraerts.sociabletesting.junit.mockito.annotations.SociableTest;

@SociableTest
@Tag("dummyTest")
public class CanNotInstantiateAbstractClassWithoutImplementationTest {
    @TestSubject
    DependsOnAbstractClassWIthoutImplementations subject;

    @Test
    void shouldFail() {
        assertThat(true).isTrue();
    }
}
