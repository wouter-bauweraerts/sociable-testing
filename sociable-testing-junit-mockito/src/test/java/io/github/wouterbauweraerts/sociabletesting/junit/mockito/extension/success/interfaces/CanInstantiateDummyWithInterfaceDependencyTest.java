package io.github.wouterbauweraerts.sociabletesting.junit.mockito.extension.success.interfaces;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import io.github.wouterbauweraerts.sociabletesting.core.annotations.TestSubject;
import io.github.wouterbauweraerts.sociabletesting.core.dummies.DummyInterfaceImpl;
import io.github.wouterbauweraerts.sociabletesting.junit.mockito.annotations.SociableTest;

@SociableTest
class CanInstantiateDummyWithInterfaceDependencyTest {
    @TestSubject
    SimpleDummyWithInterfaceDependency subject;

    @Test
    void createsExpected() {
        assertThat(subject).isNotNull();
        assertThat(subject.getInterfaceDependency()).isNotNull()
                .isInstanceOf(DummyInterfaceImpl.class);
    }
}
