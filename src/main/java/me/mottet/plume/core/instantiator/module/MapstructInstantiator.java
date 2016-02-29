package me.mottet.plume.core.instantiator.module;


import me.mottet.plume.core.ReflectionUtil;
import me.mottet.plume.core.api.Instantiator;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapstructInstantiator implements Instantiator {

    private final Map<Class, Object> instantiatedServices;

    public MapstructInstantiator(ReflectionUtil reflectionUtil) {
        this.instantiatedServices = instantiateMappers(reflectionUtil);
    }

    @Override
    public Map<Class, Object> getInstances() {
        return instantiatedServices;
    }

    private Map<Class, Object> instantiateMappers(ReflectionUtil reflectionUtil) {
        Set<Class<?>> mappersToInstantiate = reflectionUtil.getReflections().getTypesAnnotatedWith(Mapper.class);
        return mappersToInstantiate.stream()
                .collect(Collectors.toMap(Function.identity(), this::instantiateMapper));
    }

    private Object instantiateMapper(Class<?> mapperClass) {
        return Mappers.getMapper(mapperClass);
    }
}
