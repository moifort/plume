package me.mottet.plume.core;

import me.mottet.plume.annotation.EnablePlume;
import me.mottet.plume.annotation.Inject;
import me.mottet.plume.annotation.Service;
import me.mottet.plume.exception.PlumeAnnotationException;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
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

    public Set<Class<?>> getClassAnnotatedServices() {
        HashSet<Class<?>> services = new HashSet<>();
        services.addAll(reflections.getTypesAnnotatedWith(Service.class));
        return services;
    }

    public Set<Method> getMethodAnnotatedSercices() {
        HashSet<Method> metho = new HashSet<>();
        metho.addAll(reflections.getMethodsAnnotatedWith(Service.class));
        return metho;
    }

    public Set<Field> getInjectedFields() {
        return reflections.getFieldsAnnotatedWith(Inject.class);
    }
}
