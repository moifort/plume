package me.mottet.plume.core.instantiator;

import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AnnotatedMethodInstantiatorTest {

    private AnnotatedMethodInstantiator annotatedMethodInstantiator;

    @Test
    public void testGetInstances_ShouldReturn_InstantiatedClass() throws Exception {
        // Setup
        annotatedMethodInstantiator = new AnnotatedMethodInstantiator(Collections.singleton(ServiceMethodToInstantiate.class.getMethod("serviceToInstantiate")));

        // Exercise
        Map<Class, Object> instantiatedService = annotatedMethodInstantiator.getInstances();

        // Verify
        assertThat(instantiatedService).hasSize(1);
        assertThat(instantiatedService.get(ServiceToInstantiate.class)).isExactlyInstanceOf(ServiceToInstantiate.class);
        assertThat(((ServiceToInstantiate)instantiatedService.get(ServiceToInstantiate.class)).returnTrue()).isTrue();
    }

    @Test
    public void testGetInstances_ShouldReturn_EmptyCollection_When_NoMethod() {
        // Setup
        annotatedMethodInstantiator = new AnnotatedMethodInstantiator(Collections.emptySet());

        // Exercise
        Map<Class, Object> instantiatedService = annotatedMethodInstantiator.getInstances();

        // Verify
        assertThat(instantiatedService).isEmpty();
    }

    public static class ServiceMethodToInstantiate {

        public ServiceToInstantiate serviceToInstantiate() {
            return new ServiceToInstantiate();
        }
    }

    public static class ServiceToInstantiate {

        public boolean returnTrue() {
            return true;
        }
    }
}