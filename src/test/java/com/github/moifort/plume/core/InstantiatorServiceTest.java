package com.github.moifort.plume.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InstantiatorServiceTest {

    /*private InstantiatorService instantiatorService;
    private InitClass initClass;

    @Mock private Reflections reflections;

    @Before
    public void setUp() throws NoSuchMethodException {
        initClass = new InitClass();

        when(reflections.getTypesAnnotatedWith(Service.class))
                .thenReturn(Collections.singleton(ServiceOne.class));
        when(reflections.getMethodsAnnotatedWith(Service.class))
                .thenReturn(Collections.singleton(ServiceMethodToInstantiate.class.getMethod("serviceToInstantiate")));
        //instantiatorService = new InstantiatorService(initClass, reflections);
    }

    @Test
    public void testGetInstantiateObject_ShouldReturn_AnnotatedClassInstance() {
        // Exercise
        Map<Class, Object> instantiatedObject = instantiatorService.getInstantiatedObject();

        // Verify
        assertThat(instantiatedObject).isNotEmpty();
        assertThat(instantiatedObject.get(ServiceToInstantiateThroughMethod.class)).isExactlyInstanceOf(ServiceToInstantiateThroughMethod.class);
        assertThat(((ServiceToInstantiateThroughMethod)instantiatedObject.get(ServiceToInstantiateThroughMethod.class)).returnTrue()).isTrue();
    }

    @Test
    public void testGetInstantiateObject_ShouldReturn_AnnotatedMethodInstance() {
        // Exercise
        Map<Class, Object> instantiatedObject = instantiatorService.getInstantiatedObject();

        // Verify
        assertThat(instantiatedObject).isNotEmpty();
        assertThat(instantiatedObject.get(ServiceOne.class)).isExactlyInstanceOf(ServiceOne.class);
        assertThat(((ServiceOne)instantiatedObject.get(ServiceOne.class)).returnTrue()).isTrue();
    }

    @Test
    public void testGetInstantiateObject_ShouldReturn_InstanceMappedWithInterfaceClass() {
        // Exercise
        Map<Class, Object> instantiatedObject = instantiatorService.getInstantiatedObject();

        // Verify
        assertThat(instantiatedObject).isNotEmpty();
        assertThat(instantiatedObject.get(InterfaceOne.class)).isExactlyInstanceOf(ServiceOne.class);
        assertThat(((InterfaceOne)instantiatedObject.get(InterfaceOne.class)).returnTrue()).isTrue();
    }

    @Test(expected = PlumeAnnotationException.class)
    public void testGetInstantiateObject_ShouldThrow_Exception_When_DuplicateClassInstance() throws NoSuchMethodException {
        // setup
        when(reflections.getTypesAnnotatedWith(Service.class))
                .thenReturn(Collections.singleton(ServiceOne.class));
        when(reflections.getMethodsAnnotatedWith(Service.class))
                .thenReturn(Collections.singleton(SameServiceMethodToInstantiate.class.getMethod("serviceToInstantiate")));
        //instantiatorService = new InstantiatorService(initClass, reflections);

        // Exercise
        instantiatorService.getInstantiatedObject();
    }*/

    @Test
    public void testName_Should_When() {
        // Setup

        // Exercise

        // Verify
    }

    private static class InitClass {

        public static void main(String... args) {

        }
    }

    public interface InterfaceOne {

        boolean returnTrue();
    }

    public static class ServiceOne implements InterfaceOne{

        @Override
        public boolean returnTrue() {
            return true;
        }
    }

    public static class ServiceMethodToInstantiate {

        public ServiceToInstantiateThroughMethod serviceToInstantiate() {
            return new ServiceToInstantiateThroughMethod();
        }
    }

    public static class SameServiceMethodToInstantiate {

        public ServiceOne serviceToInstantiate() {
            return new ServiceOne();
        }
    }

    public static class ServiceToInstantiateThroughMethod {

        public boolean returnTrue() {
            return true;
        }
    }

}