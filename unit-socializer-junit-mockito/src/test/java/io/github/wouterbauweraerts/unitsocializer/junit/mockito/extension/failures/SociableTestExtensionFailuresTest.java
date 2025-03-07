package io.github.wouterbauweraerts.unitsocializer.junit.mockito.extension.failures;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectMethod;
import static org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder.request;

import org.junit.jupiter.api.Test;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import io.github.wouterbauweraerts.unitsocializer.core.exception.SociableTestException;
import io.github.wouterbauweraerts.unitsocializer.junit.mockito.extension.failures.abstractclasses.CanNotInstantiateAbstractClassWithMultipleImplementationTest;
import io.github.wouterbauweraerts.unitsocializer.junit.mockito.extension.failures.abstractclasses.CanNotInstantiateAbstractClassWithoutImplementationTest;
import io.github.wouterbauweraerts.unitsocializer.junit.mockito.extension.failures.interfaces.CanNotInstantiateInterfaceWithMultipleImplementationTest;
import io.github.wouterbauweraerts.unitsocializer.junit.mockito.extension.failures.interfaces.CanNotInstantiateInterfaceWithoutImplementationTest;

class SociableTestExtensionFailuresTest {

    @Test
    void startupShouldFailWhenNoTestSubject() {
        TestExecutionSummary summary = runTestMethod(CanNotStartSociableTestWithoutTestSubjectTest.class, "shouldFail");

        assertThat(summary.getFailures()).hasSize(1)
                .allSatisfy(failure -> assertThat(failure.getException()).isInstanceOf(SociableTestException.class)
                        .hasMessage("No fields annotated with @TestSubject found!"));
    }

    @Test
    void startupShouldFailWhenNoImplementationForInterface() {
        TestExecutionSummary summary = runTestMethod(CanNotInstantiateInterfaceWithoutImplementationTest.class, "shouldFail");

        assertThat(summary.getFailures()).hasSize(1)
                .allSatisfy(failure -> assertThat(failure.getException()).isInstanceOf(SociableTestException.class)
                        .hasMessage("Unable to determine type to instantiate for abstract type DummyInterfaceNoImplementations. No implementations"));
    }

    @Test
    void startupShouldFailWhenMultipleImplementationForInterface() {
        TestExecutionSummary summary = runTestMethod(CanNotInstantiateInterfaceWithMultipleImplementationTest.class, "shouldFail");

        assertThat(summary.getFailures()).hasSize(1)
                .allSatisfy(failure -> assertThat(failure.getException()).isInstanceOf(SociableTestException.class)
                        .hasMessage("Unable to determine type to instantiate for abstract type DummyInterfaceMultipleImplementations. Multiple implementations"));
    }

    @Test
    void startupShouldFailWhenNoImplementationForAbstractClass() {
        TestExecutionSummary summary = runTestMethod(CanNotInstantiateAbstractClassWithoutImplementationTest .class, "shouldFail");

        assertThat(summary.getFailures()).hasSize(1)
                .allSatisfy(failure -> assertThat(failure.getException()).isInstanceOf(SociableTestException.class)
                        .hasMessage("Unable to determine type to instantiate for abstract type DummyAbstractClassNoImplementations. No implementations"));
    }

    @Test
    void startupShouldFailWhenMultipleImplementationForAbstractClass() {
        TestExecutionSummary summary = runTestMethod(CanNotInstantiateAbstractClassWithMultipleImplementationTest .class, "shouldFail");

        assertThat(summary.getFailures()).hasSize(1)
                .allSatisfy(failure -> assertThat(failure.getException()).isInstanceOf(SociableTestException.class)
                        .hasMessage("Unable to determine type to instantiate for abstract type DummyAbstractClassMultipleImplementations. Multiple implementations"));
    }

    private TestExecutionSummary runTestMethod(Class<?> testClass, String methodName) {
        SummaryGeneratingListener listener = new SummaryGeneratingListener();

        LauncherDiscoveryRequest request = request().selectors(selectMethod(testClass, methodName)).build();
        LauncherFactory.create().execute(request, listener);

        return listener.getSummary();
    }
}
