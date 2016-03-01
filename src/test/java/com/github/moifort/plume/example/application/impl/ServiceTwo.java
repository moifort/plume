package com.github.moifort.plume.example.application.impl;

import com.github.moifort.plume.annotation.Inject;
import com.github.moifort.plume.annotation.Service;
import com.github.moifort.plume.example.application.service.ServiceThree;

@Service
public class ServiceTwo {

    @Inject private ServiceOne serviceOne;
    @Inject private ServiceThree serviceThree;

    public boolean serviceOneIsTrue() {
        return serviceOne.returnTrue();
    }

    public boolean serviceThreeIsTrue() {
        return serviceThree.returnTrue();
    }

}
