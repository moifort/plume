package com.github.moifort.plume.application.impl;

import com.github.moifort.plume.annotation.Service;

public class ServicesDefinition {

    @Service
    public ExternalService getExternalService() {
        return new ExternalService();
    }
}
