package io.github.wouterbauweraerts.sociabletesting.junit.mockito.extension.success;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import io.github.wouterbauweraerts.sociabletesting.core.annotations.TestSubject;
import io.github.wouterbauweraerts.sociabletesting.core.dummies.SimpleDummy;
import io.github.wouterbauweraerts.sociabletesting.junit.mockito.annotations.SociableTest;

@SociableTest
class CanInstantiateSimpleDummyWithoutDependenciesTest {
    @TestSubject
    SimpleDummy simpleDummy;

    @Test
    void test() {
        assertThat(simpleDummy).isNotNull();
    }
}
