package me.mottet.plume.application;

import me.mottet.plume.annotation.EnablePlume;
import me.mottet.plume.annotation.Inject;
import me.mottet.plume.core.Plume;
import me.mottet.plume.application.impl.ServiceTwo;

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

        // Service with interface implementation
        serviceTwo.serviceThreeIsTrue();
    }
}
