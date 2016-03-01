package com.github.moifort.plume;

import com.github.moifort.plume.application.Application;
import org.junit.Test;

public class PlumeTest {


    @Test
    public void testApplication_ShouldWork_WithoutError() {
        // Setup
        Application application = new Application();

        // Exercise
        application.main();
    }
}