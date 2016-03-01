package com.github.moifort.plume.core;

import com.github.moifort.plume.core.api.Instantiator;

import java.util.List;
import java.util.Map;

public class InstantiatorService {

    private final Map<Class, Object> instantiatedObject;

    public InstantiatorService(List<Instantiator> defaultInstatiators,
                               List<Instantiator> moduleInstatiators,
                               InterfaceResolverService callingClassInstance) {
        this.instantiatedObject = null;
    }

    public Map<Class, Object> getInstantiatedObject() {
        return instantiatedObject;
    }

   /* private void toto(){

        try {
            Map<Class, Object> classObjectMap = getAllInstantiator().stream()
                    .map(Instantiator::getInstances)
                    .map(Map::entrySet)
                    .flatMap(Collection::stream)
                    .collect(
                            Collectors.toMap(
                                    Map.Entry::getKey,
                                    Map.Entry::getValue
                            )
                    );

            classObjectMap.putAll(new InterfaceInstantiator(classObjectMap).getInstances());
            return classObjectMap;
        }
        catch (IllegalStateException e) {
            throw new PlumeAnnotationException("You cannot annotated the same class as service more than once",e);
        }
    }

    private List<Instantiator> getAllInstantiator() {
        Stream<List<Instantiator>> streamAllInstantiator = Stream.of(
                getDefaultInstantiators(),
                getModuleInstantiators());
        return streamAllInstantiator.flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Instantiator> getDefaultInstantiators() {
        return Arrays.asList(
                new MainClassInstantiator(callingClassInstance),
                new AnnotatedClassInstantiator(reflections.getTypesAnnotatedWith(Service.class)),
                new AnnotatedMethodInstantiator(reflections.getMethodsAnnotatedWith(Service.class))
        );
    }

    private List<Instantiator> getModuleInstantiators() {
        return Arrays.asList(new MapstructInstantiator(reflections.getTypesAnnotatedWith(Mapper.class)));
    }*/
}
