package me.mottet.plume.impl;

import me.mottet.plume.annotation.Service;

public class ServicesDefinition {

    @Service
    public ExternalService getExternalService() {
        return new ExternalService();
    }
}
