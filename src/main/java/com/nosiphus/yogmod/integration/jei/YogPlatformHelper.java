package com.nosiphus.yogmod.integration.jei;

import mezz.jei.core.util.function.LazySupplier;

import java.util.function.Supplier;

public class YogPlatformHelper implements IYogPlatformHelper {

    private final Supplier<YogRecipeHelper> yogRecipeHelper = new LazySupplier(YogRecipeHelper::new);

    public YogPlatformHelper() {

    }

    public YogRecipeHelper getYogRecipeHelper() {
        return (YogRecipeHelper) this.yogRecipeHelper.get();
    }

}
