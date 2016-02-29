package me.mottet.plume.application.impl;

import me.mottet.plume.annotation.Service;

@Service
public class ServiceOne {

    public boolean returnTrue() {
        return true;
    }

    public boolean returnFalse() {
        return false;
    }
}
