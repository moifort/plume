package me.mottet.plume.application;

import me.mottet.plume.annotation.EnablePlume;
import me.mottet.plume.annotation.Inject;
import me.mottet.plume.core.Plume;
import me.mottet.plume.impl.ServiceTwo;

@EnablePlume
public class ApplicationTest {

    @Inject private ServiceTwo serviceTwo;

    public static void main(String... args) {
        new ApplicationTest().run();
    }

    public void run() {
        new Plume().start(this);

        // Normal service
        serviceTwo.serviceOneIsTrue();

        // Service with interface implementation
        serviceTwo.serviceThreeIsTrue();
    }
}
