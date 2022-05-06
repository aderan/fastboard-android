package io.agora.board.fast.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorInt;

import io.agora.board.fast.extension.ResourceImpl;
import io.agora.board.fast.model.FastAppliance;

public class ResourceFetcher {
    private static ResourceFetcher instance;
    private ResourceImpl resourceImpl;

    private ResourceFetcher() {
        resourceImpl = new ResourceImpl();
    }

    public static synchronized ResourceFetcher get() {
        if (instance == null) {
            instance = new ResourceFetcher();
        }
        return instance;
    }

    public void init(Context context) {
        resourceImpl.init(context);
    }

    public void setResourceImpl(ResourceImpl resourceImpl) {
        this.resourceImpl = resourceImpl;
    }

    public Drawable getBackground(boolean darkMode) {
        return resourceImpl.getBackground(darkMode);
    }

    public Drawable getLayoutBackground(boolean darkMode) {
        return resourceImpl.getLayoutBackground(darkMode);
    }

    public Drawable getButtonBackground(boolean darkMode) {
        return resourceImpl.getButtonBackground(darkMode);
    }

    public @ColorInt int getBackgroundColor(boolean darkMode) {
        return resourceImpl.getBackgroundColor(darkMode);
    }

    public ColorStateList getIconColor(boolean darkMode) {
        return resourceImpl.getIconColor(darkMode);
    }

    public ColorStateList getIconColor(boolean darkMode, boolean changeEnable) {
        return resourceImpl.getIconColor(darkMode, changeEnable);
    }

    public ColorStateList getTextColor(boolean darkMode) {
        return resourceImpl.getTextColor(darkMode);
    }

    public Drawable createColorBackground(int mainColor) {
        return resourceImpl.createColorBackground(mainColor);
    }

    public Drawable createApplianceBackground(boolean darkMode) {
        return resourceImpl.createApplianceBackground(darkMode);
    }

    public Drawable getColorDrawable(int color) {
        return resourceImpl.getColorDrawable(color);
    }

    public int getApplianceIcon(FastAppliance fastAppliance) {
        return resourceImpl.getApplianceIcon(fastAppliance);
    }
}
