package com.github.moifort.plume.core;

import com.github.moifort.plume.annotation.Service;
import com.github.moifort.plume.core.api.Instantiator;
import com.github.moifort.plume.exception.PlumeInternalException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InjectResolverService {

    private final ReflectionUtil reflectionUtil;
    private final List<Instantiator> instantiators;

    public InjectResolverService(ReflectionUtil reflectionUtil, List<Instantiator> instantiators) {
        this.reflectionUtil = reflectionUtil;
        this.instantiators = instantiators;
    }

    public void injectServices() {
        Set<Field> fieldsToInject = reflectionUtil.getInjectedFields();

        HashMap<Class, Object> instantiatedServices = getAllInstantiatedServices(instantiators);
        fieldsToInject.stream().forEach(field -> injectService(field, instantiatedServices));
    }

    private HashMap<Class, Object> getAllInstantiatedServices(List<Instantiator> instantiators){
        HashMap<Class, Object> allInstantiatedServices = new HashMap<>();
        instantiators.stream()
                .forEach(serviceInstantiator -> allInstantiatedServices.putAll(serviceInstantiator.getInstances()));
        return allInstantiatedServices;
    }

    private static void injectService(Field field, Map<Class, Object> instanciatedServices) {
        try {
            Object serviceFieldBelongs = getBelongedService(instanciatedServices, field.getDeclaringClass());

            Object fieldType = getFieldType(instanciatedServices, field.getType());

            field.setAccessible(true);
            field.set(serviceFieldBelongs, fieldType);
        } catch (IllegalAccessException e) {
            throw new PlumeInternalException("Problem " + field.getType(), e);
        }
    }

    private static Object getFieldType(Map<Class, Object> instanciatedServices, Class<?> type) {
        Object fieldType = instanciatedServices.get(type);
        if (fieldType == null)
            throw new PlumeInternalException("Missing @" + Service.class.getSimpleName() + " on " + type + ", you try to inject non identified service");
        return fieldType;
    }

    private static Object getBelongedService(Map<Class, Object> instanciatedServices, Class<?> declaringClass) {
        Object serviceFieldBelongs = instanciatedServices.get(declaringClass);
        if (serviceFieldBelongs == null)
            throw new PlumeInternalException("Missing @" + Service.class.getSimpleName() + " on " + declaringClass + ", you try to inject service inside a non service class");
        return serviceFieldBelongs;
    }
}
