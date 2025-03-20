package com.nosiphus.yogmod.core.dispenser;

public abstract class YogOptionalDispenseItemBehavior extends YogDefaultDispenseItemBehavior {
    private boolean success = true;

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    protected void playSound(YogBlockSource yogBlockSource) {
        yogBlockSource.level().levelEvent(this.isSuccess() ? 1000 : 1001, yogBlockSource.pos(), 0);
    }
}
