package com.nosiphus.yogmod.integration.jei;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ServiceLoader;

public class YogServices {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final IYogPlatformHelper PLATFORM = (IYogPlatformHelper) load(IYogPlatformHelper.class);

    public YogServices() {
    }

    public static <T> T load(Class<T> serviceClass) {
        T loadedService = ServiceLoader.load(serviceClass).findFirst().orElseThrow(() -> {
            return new NullPointerException("Failed to load service for " + serviceClass.getName());
        });
        LOGGER.debug("Loaded {} for service {}", loadedService, serviceClass);
        return loadedService;
    }

}
