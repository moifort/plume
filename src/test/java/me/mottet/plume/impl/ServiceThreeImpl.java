package me.mottet.plume.impl;

import me.mottet.plume.annotation.Service;
import me.mottet.plume.service.ServiceThree;

@Service
public class ServiceThreeImpl implements ServiceThree {

    @Override
    public boolean returnTrue() {
        return true;
    }
}
