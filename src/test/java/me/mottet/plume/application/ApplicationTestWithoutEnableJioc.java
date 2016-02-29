package me.mottet.plume.application;

import me.mottet.plume.core.Plume;

public class ApplicationTestWithoutEnableJioc {

    public static void main(String... args) {
        new ApplicationTestWithoutEnableJioc().run();
    }

    public void run() {
        new Plume().start(this);
    }
}
