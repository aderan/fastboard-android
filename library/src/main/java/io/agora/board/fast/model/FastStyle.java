package io.agora.board.fast.model;

import androidx.annotation.ColorInt;

/**
 * @author fenglibin
 */
public class FastStyle {
    private int mainColor;
    private boolean darkMode;

    public FastStyle() {
    }

    public int getMainColor() {
        return mainColor;
    }

    public void setMainColor(@ColorInt int color) {
        this.mainColor = color;
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public FastStyle copy() {
        FastStyle style = new FastStyle();
        style.mainColor = mainColor;
        style.darkMode = darkMode;
        return style;
    }
}
