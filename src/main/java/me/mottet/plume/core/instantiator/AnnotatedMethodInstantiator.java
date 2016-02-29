package me.mottet.plume.core.instantiator;


import me.mottet.plume.annotation.Service;
import me.mottet.plume.core.ReflectionUtil;
import me.mottet.plume.core.api.Instantiator;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class AnnotatedMethodInstantiator implements Instantiator {

    private final Map<Class, Object> instantiatedServices;

    public AnnotatedMethodInstantiator(ReflectionUtil reflectionUtil) {
        this.instantiatedServices = instantiateServiceMethod(reflectionUtil);
    }

    @Override
    public Map<Class, Object> getInstances() {
        return instantiatedServices;
    }

    private Map<Class, Object> instantiateServiceMethod(ReflectionUtil reflectionUtil) {
        Set<Method> mappersToInstantiate = reflectionUtil.getReflections().getMethodsAnnotatedWith(Service.class);
        return Collections.emptyMap();
    }

}
