package me.mottet.plume.impl;

import me.mottet.plume.annotation.Inject;
import me.mottet.plume.annotation.Service;
import me.mottet.plume.service.ServiceThree;

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
