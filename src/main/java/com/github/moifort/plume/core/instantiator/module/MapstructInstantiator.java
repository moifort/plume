package com.github.moifort.plume.core.instantiator.module;


import com.github.moifort.plume.core.api.Instantiator;
import org.mapstruct.factory.Mappers;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapstructInstantiator implements Instantiator {

    private final Map<Class, Object> instantiatedServices;

    public MapstructInstantiator(Set<Class<?>> mappersToInstantiate) {
        this.instantiatedServices = instantiateMappers(mappersToInstantiate);
    }

    @Override
    public Map<Class, Object> getInstances() {
        return instantiatedServices;
    }

    private Map<Class, Object> instantiateMappers(Set<Class<?>> mappersToInstantiate) {
        return mappersToInstantiate.stream()
                .collect(Collectors.toMap(Function.identity(), this::instantiateMapper));
    }

    private Object instantiateMapper(Class<?> mapperClass) {
        return Mappers.getMapper(mapperClass);
    }
}
