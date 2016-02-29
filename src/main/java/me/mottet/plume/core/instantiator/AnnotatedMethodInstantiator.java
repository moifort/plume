package me.mottet.plume.core.instantiator;


import me.mottet.plume.core.api.Instantiator;
import me.mottet.plume.exception.PlumeInternalException;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnnotatedMethodInstantiator implements Instantiator {

    private final Map<Class, Object> instantiatedServices;

    public AnnotatedMethodInstantiator(Set<Method> annotedMethodToInstantiate) {
        this.instantiatedServices = instantiateAnnotatedMethods(annotedMethodToInstantiate);
    }

    @Override
    public Map<Class, Object> getInstances() {
        return instantiatedServices;
    }

    private Map<Class, Object> instantiateAnnotatedMethods(Set<Method> annotatedMethodToInstantiate) {
        return annotatedMethodToInstantiate.stream()
                .map(this::instantiateAnnotatedMethod)
                .collect(Collectors.toMap(Object::getClass, Function.identity()));
    }

    private Object instantiateAnnotatedMethod(Method method) {
        Object declaringInstance = instantiateDeclaringClass(method.getDeclaringClass());
        try {
            return method.invoke(declaringInstance);
        } catch (Exception e) {
            throw new PlumeInternalException("Problem to instantiate method service: " + method.getName(), e);
        }
    }

    private Object instantiateDeclaringClass(Class<?> classToInstantiate) {
        try {
            return classToInstantiate.newInstance();
        } catch (Exception e) {
            throw new PlumeInternalException("Problem with: " + classToInstantiate.getName(), e);
        }
    }

}
