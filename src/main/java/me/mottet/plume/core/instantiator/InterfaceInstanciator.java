package me.mottet.plume.core.instantiator;

import me.mottet.plume.core.api.Instantiator;
import me.mottet.plume.exception.PlumeAnnotationException;
import org.reflections.ReflectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InterfaceInstanciator implements Instantiator {

    private final Map<Class, Object> allInstantiateClass;

    public InterfaceInstanciator(List<Instantiator> instantiators) {
        allInstantiateClass = new HashMap<>();
        instantiators.forEach(instantiator -> allInstantiateClass.putAll(instantiator.getInstances()));
    }

    @Override
    public Map<Class, Object> getInstances() {
        return associateInterfacesToServicesInstance(allInstantiateClass);
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
            throw new PlumeAnnotationException("Interface(s) are implemented by two services. List below:\n" + getInterfacesListName(interfaceError));
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
