package com.github.moifort.plume.core;

import com.github.moifort.plume.annotation.EnablePlume;
import com.github.moifort.plume.annotation.Inject;
import com.github.moifort.plume.exception.PlumeAnnotationException;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.util.Set;

public class ReflectionUtil {

    private final Reflections reflections;

    public ReflectionUtil(Class<?> callingClassInstance) {
        if (!callingClassInstance.isAnnotationPresent(EnablePlume.class))
            throw new PlumeAnnotationException("Missing @"+EnablePlume.class.getSimpleName()+", add the annotation to the class that start Plume");

        reflections = new Reflections(
                new ConfigurationBuilder()
                        .setUrls(callingClassInstance.getResource(""))
                        .setScanners(new TypeAnnotationsScanner(), new FieldAnnotationsScanner()));
    }

    public Reflections getReflections() {
        return reflections;
    }

    public Set<Field> getInjectedFields() {
        return reflections.getFieldsAnnotatedWith(Inject.class);
    }
}
