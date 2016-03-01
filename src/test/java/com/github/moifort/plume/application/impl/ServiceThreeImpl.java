package com.github.moifort.plume.application.impl;

import com.github.moifort.plume.annotation.Service;
import com.github.moifort.plume.application.service.ServiceThree;

@Service
public class ServiceThreeImpl implements ServiceThree {

    @Override
    public boolean returnTrue() {
        return true;
    }
}
