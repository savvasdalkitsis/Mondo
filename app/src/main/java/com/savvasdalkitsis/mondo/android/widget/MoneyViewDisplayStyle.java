package com.savvasdalkitsis.mondo.android.widget;

enum MoneyViewDisplayStyle {

    LARGE(0), SMALL(1);

    private int styleCode;

    MoneyViewDisplayStyle(int styleCode) {
        this.styleCode = styleCode;
    }

    public int getStyleCode() {
        return styleCode;
    }

    public static MoneyViewDisplayStyle defaultStyle() {
        return LARGE;
    }

    public static MoneyViewDisplayStyle from(int style) {
        for (MoneyViewDisplayStyle displayStyle : values()) {
            if (displayStyle.styleCode == style) {
                return displayStyle;
            }
        }
        return defaultStyle();
    }
}
