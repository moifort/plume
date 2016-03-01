package com.github.moifort.plume.core;

import com.github.moifort.plume.exception.PlumeAnnotationException;
import org.reflections.ReflectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InterfaceResolverService {
    private final Map<Class, Object> interfaceMapWithInstance;

    public InterfaceResolverService(Map<Class, Object> allInstantiatedClasses) {
        this.interfaceMapWithInstance = associateInterfacesToServicesInstance(allInstantiatedClasses);
    }

    public Map<Class, Object> getInstances() {
        return interfaceMapWithInstance;
    }

    private Map<Class, Object> associateInterfacesToServicesInstance(Map<Class, Object> instantiatedServicesOnly) {
        HashMap<Class, Object> interfaceAssociatedToServicesInstance = new HashMap<>();
        for (Object service : instantiatedServicesOnly.values()) {
            Map<Class, Object> interfaceAssociatedToServiceInstance = associateInterfacesToServiceInstance(service);
            ifInterfaceIsImplementedByMoreThanOneServiceThrowException(interfaceAssociatedToServiceInstance, interfaceAssociatedToServicesInstance);
            interfaceAssociatedToServicesInstance.putAll(interfaceAssociatedToServiceInstance);
        }
        return interfaceAssociatedToServicesInstance;
    }

    private void ifInterfaceIsImplementedByMoreThanOneServiceThrowException(Map<Class, Object> interfaces, Map<Class, Object> services) {
        Set<Class> interfaceError = getAllInterfaceWithMultiImplements(interfaces.keySet(), services.keySet());
        if (interfaceError.size() > 0)
            throw new PlumeAnnotationException("Interface(s) cannot be implemented by more than one service. List below:\n" + getInterfacesListName(interfaceError));
    }

    private String getInterfacesListName(Set<Class> interfacesClass) {
        StringBuffer stringBuffer = new StringBuffer();
        List<String> interfacesNames = ReflectionUtils.names(interfacesClass.toArray(new Class[interfacesClass.size()]));
        interfacesNames.stream().forEach(name -> stringBuffer.append(" - ").append(name).append("\n"));
        return stringBuffer.toString();
    }

    private Set<Class> getAllInterfaceWithMultiImplements(Set<Class> interfacesClass, Set<Class> servicesClass) {
        return interfacesClass.stream()
                .filter(interfaceClass -> isClassAlreadyPresent(interfaceClass, servicesClass))
                .collect(Collectors.toSet());
    }

    private boolean isClassAlreadyPresent(Class interfaceClass,Set<Class> servicesClass) {
        return servicesClass.contains(interfaceClass);
    }

    private Map<Class, Object> associateInterfacesToServiceInstance(Object service) {
        List<Class<?>> implementedInterface = getImplementedInterface(service);
        return implementedInterface.stream()
                .collect(Collectors.toMap(Function.identity(), interfaceClass -> service));
    }

    private List<Class<?>> getImplementedInterface(Object serviceInstance) {
        return Arrays.asList(serviceInstance.getClass().getInterfaces());
    }
}
