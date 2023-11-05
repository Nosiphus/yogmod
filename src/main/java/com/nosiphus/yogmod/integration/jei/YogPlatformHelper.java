package com.nosiphus.yogmod.integration.jei;

import mezz.jei.core.util.function.CachedSupplier;

public class YogPlatformHelper implements IYogPlatformHelper {

    private final CachedSupplier<YogRecipeHelper> yogRecipeHelper = new CachedSupplier(YogRecipeHelper::new);

    public YogPlatformHelper() {

    }

    public YogRecipeHelper getYogRecipeHelper() {
        return (YogRecipeHelper) this.yogRecipeHelper.get();
    }

}
