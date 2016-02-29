package me.mottet.plume.core.instantiator;

import me.mottet.plume.exception.PlumeAnnotationException;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AnnotatedClassInstantiatorTest {

    private AnnotatedClassInstantiator annotatedClassInstantiator;


    @Test
    public void testGetInstances_ShouldReturn_InstantiatedClass() {
        // Setup
        annotatedClassInstantiator = new AnnotatedClassInstantiator(Collections.singleton(ServiceToInstantiate.class));

        // Exercise
        Map<Class, Object> instantiatedService = annotatedClassInstantiator.getInstances();

        // Verify
        assertThat(instantiatedService).hasSize(1);
        assertThat(instantiatedService.get(ServiceToInstantiate.class)).isExactlyInstanceOf(ServiceToInstantiate.class);
        assertThat(((ServiceToInstantiate)instantiatedService.get(ServiceToInstantiate.class)).returnTrue()).isTrue();
    }

    @Test
    public void testGetInstances_ShouldReturn_EmptyCollection_When_NoClasses() {
        // Setup
        annotatedClassInstantiator = new AnnotatedClassInstantiator(Collections.emptySet());

        // Exercise
        Map<Class, Object> instantiatedService = annotatedClassInstantiator.getInstances();

        // Verify
        assertThat(instantiatedService).isEmpty();
    }


    @Test(expected = PlumeAnnotationException.class)
    public void testGetInstances_ShouldThrow_When_InterfaceIsAnnotated() {
        // Setup
        annotatedClassInstantiator = new AnnotatedClassInstantiator(Collections.singleton(ServiceInterfaceToInstantiate.class));

        // Exercise
        annotatedClassInstantiator.getInstances();
    }

    @Test(expected = PlumeAnnotationException.class)
    public void testGetInstances_ShouldThrow_When_ClassHasConstructorWithArgument() {
        // Setup
        annotatedClassInstantiator = new AnnotatedClassInstantiator(Collections.singleton(ServiceWithArgumentToInstantiate.class));

        // Exercise
        annotatedClassInstantiator.getInstances();
    }

    @Test(expected = PlumeAnnotationException.class)
    public void testGetInstances_ShouldThrow_When_ClassHasProblem() {
        // Setup
        annotatedClassInstantiator = new AnnotatedClassInstantiator(Collections.singleton(ServiceWithProblemToInstantiate.class));

        // Exercise
        annotatedClassInstantiator.getInstances();
    }

    public static class ServiceWithProblemToInstantiate {


        public ServiceWithProblemToInstantiate() throws Exception {
            throw new Exception("Error");
        }

        public boolean returnTrue() {
            return true;
        }
    }


    public static class ServiceWithArgumentToInstantiate {

        private final boolean isTrue;

        public ServiceWithArgumentToInstantiate(boolean isTrue) {
            this.isTrue = isTrue;
        }

        public boolean returnTrue() {
            return isTrue;
        }
    }


    public static class ServiceToInstantiate {

        public boolean returnTrue() {
            return true;
        }
    }

    public interface ServiceInterfaceToInstantiate {

        public boolean returnTrue();

    }
}