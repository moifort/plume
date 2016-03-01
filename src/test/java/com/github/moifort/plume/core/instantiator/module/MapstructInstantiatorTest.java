package com.github.moifort.plume.core.instantiator.module;

import org.junit.Test;
import org.mapstruct.Mapper;

public class MapstructInstantiatorTest {

    private MapstructInstantiator mapstructInstantiator;

    @Test
    public void testGetInstances_ShouldReturn_InstantiatedMapper() {
        // Setup

    }

    @Mapper
    public static interface ThingMapper {
        ThingDTO thingToThingDTO(Thing thing);
    }

    public static class Thing {
        private String label;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class ThingDTO {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}