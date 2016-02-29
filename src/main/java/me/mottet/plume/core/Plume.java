package me.mottet.plume.core;

import me.mottet.plume.core.api.Instantiator;
import me.mottet.plume.core.instantiator.*;
import me.mottet.plume.core.instantiator.module.MapstructInstantiator;

import java.util.ArrayList;
import java.util.List;

public class Plume {

    public void start(Object callingClassInstance) {
        // Init
        ReflectionUtil reflectionUtil = new ReflectionUtil(callingClassInstance.getClass());

        // Instantiate dependencies
        List<Instantiator> instantiators = new ArrayList<>();
        instantiators.add(new MainClassInstantiator(callingClassInstance));
        instantiators.add(new AnnotatedClassInstantiator(reflectionUtil.getClassAnnotatedServices()));
        instantiators.add(new AnnotatedMethodInstantiator(reflectionUtil.getMethodAnnotatedSercices()));
        instantiators.add(new MapstructInstantiator(reflectionUtil));
        instantiators.add(new InterfaceInstantiator(null)); // Always put a the end

        // Inject dependencies
        InjectResolver injectResolver = new InjectResolver(reflectionUtil, instantiators);

        // Run dependencies injection
        injectResolver.injectServices();
    }

}
