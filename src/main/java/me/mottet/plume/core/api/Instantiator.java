package me.mottet.plume.core.api;

import java.util.Map;

public interface Instantiator {

    Map<Class, Object> getInstances();
}
