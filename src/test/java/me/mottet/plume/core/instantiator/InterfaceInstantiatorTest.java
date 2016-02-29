package me.mottet.plume.core.instantiator;

import me.mottet.plume.exception.PlumeAnnotationException;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class InterfaceInstantiatorTest {

    private InterfaceInstantiator interfaceInstantiator;
    private ServiceWithMultiInterface serviceWithMultiInterface;
    private ServiceWithSameInterface serviceWithSameInterface;


    @Before
    public void setUp() throws Exception {
        serviceWithMultiInterface = new ServiceWithMultiInterface();
        serviceWithSameInterface = new ServiceWithSameInterface();
    }

    @Test
    public void testGetInstances_ShouldReturn_InterfacesMappedWithThisInstance_When_InstantiatedObjectHasImplementedInterfaces() {
        // Setup
        interfaceInstantiator = new InterfaceInstantiator(Collections.singletonMap(serviceWithMultiInterface.getClass(), serviceWithMultiInterface));

        // Exercise
        Map<Class, Object> instantiatedService = interfaceInstantiator.getInstances();

        // Verify
        assertThat(instantiatedService).hasSize(2);
        assertThat(instantiatedService.get(InterfaceOne.class)).isExactlyInstanceOf(ServiceWithMultiInterface.class);
        assertThat(instantiatedService.get(InterfaceTwo.class)).isExactlyInstanceOf(ServiceWithMultiInterface.class);
        assertThat(((InterfaceOne)instantiatedService.get(InterfaceOne.class)).returnTrue()).isTrue();
        assertThat(((InterfaceTwo)instantiatedService.get(InterfaceTwo.class)).returnFalse()).isFalse();
    }

    @Test(expected = PlumeAnnotationException.class)
    public void testGetInstances_ShouldThrow_Exception_When_MoreThanOneServiceImplementsTheSameInterface() {
        // Setup
        Map<Class, Object> instantiatedClass = new HashMap<>();
        instantiatedClass.put(serviceWithMultiInterface.getClass(), serviceWithMultiInterface);
        instantiatedClass.put(serviceWithSameInterface.getClass(), serviceWithSameInterface);
        interfaceInstantiator = new InterfaceInstantiator(instantiatedClass);

        // Exercise
        interfaceInstantiator.getInstances();
    }

    public interface InterfaceOne {

        boolean returnTrue();
    }

    public interface InterfaceTwo {

        boolean returnFalse();
    }

    public static class ServiceWithMultiInterface implements InterfaceOne, InterfaceTwo {

        @Override
        public boolean returnTrue() {
            return true;
        }

        @Override
        public boolean returnFalse() {
            return false;
        }
    }

    public static class ServiceWithSameInterface implements InterfaceOne {

        @Override
        public boolean returnTrue() {
            return false;
        }
    }

}