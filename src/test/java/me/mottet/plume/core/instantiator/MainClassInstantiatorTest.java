package me.mottet.plume.core.instantiator;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MainClassInstantiatorTest {

    private MainClassInstantiator mainClassInstantiator;
    private InitClass initClass;


    @Before
    public void setUp() throws Exception {
        initClass = new InitClass();
    }

    @Test
    public void testGetInstances_ShouldReturn_GivenObject() throws Exception {
        // Setup
        mainClassInstantiator = new MainClassInstantiator(initClass);

        // Exercise
        Map<Class, Object> instantiatedService = mainClassInstantiator.getInstances();

        // Verify
        assertThat(instantiatedService).hasSize(1);
        assertThat(instantiatedService.get(InitClass.class)).isExactlyInstanceOf(InitClass.class);
    }

    private static class InitClass {

        public static void main(String... args) {

        }
    }
}