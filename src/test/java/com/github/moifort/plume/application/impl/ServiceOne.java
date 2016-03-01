package com.github.moifort.plume.application.impl;

import com.github.moifort.plume.annotation.Service;

@Service
public class ServiceOne {

    public boolean returnTrue() {
        return true;
    }

    public boolean returnFalse() {
        return false;
    }
}
