package me.mottet.plume.application.impl;

import me.mottet.plume.annotation.Service;
import me.mottet.plume.application.service.ServiceThree;

@Service
public class ServiceThreeImpl implements ServiceThree {

    @Override
    public boolean returnTrue() {
        return true;
    }
}
