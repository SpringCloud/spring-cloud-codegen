package com.nepxion.skeleton.engine.entity;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

public enum SkeletonEntityType {
    TEXTFIELD("TEXTFIELD"),
    TOGGLEBUTTON("TOGGLEBUTTON"),
    CHECKBOX("CHECKBOX"),
    RADIO("RADIO"),
    COMBOBOX("COMBOBOX");

    private String value;

    private SkeletonEntityType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SkeletonEntityType fromString(String value) {
        for (SkeletonEntityType type : SkeletonEntityType.values()) {
            if (type.getValue().equalsIgnoreCase(value.trim())) {
                return type;
            }
        }

        throw new IllegalArgumentException("Mismatched type with value=" + value);
    }

    @Override
    public String toString() {
        return value;
    }
}