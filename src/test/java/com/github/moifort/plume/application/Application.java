package com.github.moifort.plume.application;

import com.github.moifort.plume.annotation.EnablePlume;
import com.github.moifort.plume.annotation.Inject;
import com.github.moifort.plume.core.Plume;
import com.github.moifort.plume.application.impl.ServiceTwo;

@EnablePlume
public class Application {

    @Inject private ServiceTwo serviceTwo;

    public static void main(String... args) {
        new Application().run();
    }

    public void run() {
        new Plume().start(this);

        // Normal service
        serviceTwo.serviceOneIsTrue();

        // ServiceOne with interface implementation
        serviceTwo.serviceThreeIsTrue();
    }
}
