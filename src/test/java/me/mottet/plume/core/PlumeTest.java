package me.mottet.plume.core;

import me.mottet.plume.application.ApplicationTest;
import me.mottet.plume.application.ApplicationTestWithoutEnableJioc;
import me.mottet.plume.exception.PlumeAnnotationException;
import org.junit.Test;

public class PlumeTest {

    @Test(expected = PlumeAnnotationException.class)
    public void testEnableJiocAnnotationShouldReturnExceptionWhenNoAnnotation() {
        // Setup
        ApplicationTestWithoutEnableJioc applicationTestWithoutEnableJioc = new ApplicationTestWithoutEnableJioc();

        // Exercise
        applicationTestWithoutEnableJioc.main();
    }

    @Test
    public void testEnableJiocAnnotation() {
        // Setup
        ApplicationTest applicationTest = new ApplicationTest();

        // Exercise
        applicationTest.main();
    }
}