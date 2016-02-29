package me.mottet.plume.application.impl;

import me.mottet.plume.annotation.Service;

public class ServicesDefinition {

    @Service
    public ExternalService getExternalService() {
        return new ExternalService();
    }
}
