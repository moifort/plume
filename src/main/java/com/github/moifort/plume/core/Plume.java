package com.github.moifort.plume.core;

import com.github.moifort.plume.core.api.Instantiator;

import java.util.List;

public class Plume {

    public void start(Object callingClassInstance) {
        // Init
        ReflectionUtil reflectionUtil = new ReflectionUtil(callingClassInstance.getClass());
        List<Instantiator> instantiators = null;//getInstantiators(callingClassInstance, reflectionUtil);
        InjectResolverService injectResolverService = new InjectResolverService(reflectionUtil, instantiators);

        // Run
        injectResolverService.injectServices();
    }





}
