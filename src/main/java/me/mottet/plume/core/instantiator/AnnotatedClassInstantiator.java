package me.mottet.plume.core.instantiator;


import me.mottet.plume.core.api.Instantiator;
import me.mottet.plume.exception.PlumeAnnotationException;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnnotatedClassInstantiator implements Instantiator {

    private final Map<Class, Object> instantiatedObject;

    public AnnotatedClassInstantiator(Set<Class<?>> serviceToInstantiate) {
        this.instantiatedObject = instantiateAnnotatedClasses(serviceToInstantiate);
    }

    @Override
    public Map<Class, Object> getInstances() {
        return instantiatedObject;
    }

    private Map<Class, Object> instantiateAnnotatedClasses(Set<Class<?>> serviceToInstantiate) {
        return serviceToInstantiate.stream()
                .collect(Collectors.toMap(Function.identity(), this::instantiateAnnotatedClass));
    }

    private Object instantiateAnnotatedClass(Class<?> serviceClass) {
        if (serviceClass.isInterface()) throw new PlumeAnnotationException("Interface cannot be annotated as a service: "+serviceClass.getName());
        try {
            return serviceClass.newInstance();
        } catch (InstantiationException e) {
            throw new PlumeAnnotationException("The service " + serviceClass.getName() + " must have a constructor without argument", e);
        } catch (Exception e) {
            throw new PlumeAnnotationException("Problem to instantiate service: " + serviceClass.getName(), e);
        }
    }
}
