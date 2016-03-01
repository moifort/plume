package com.github.moifort.plume.core.instantiator;


import com.github.moifort.plume.core.api.Instantiator;

import java.util.Collections;
import java.util.Map;

public class MainClassInstantiator implements Instantiator {

    private final Map<Class, Object> instantiatedServices;

    public MainClassInstantiator(Object callingClassInstance) {
        this.instantiatedServices = Collections.singletonMap(callingClassInstance.getClass(), callingClassInstance);
    }

    @Override
    public Map<Class, Object> getInstances() {
        return instantiatedServices;
    }
}
