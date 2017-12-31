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

import com.nepxion.skeleton.engine.constant.SkeletonConstant;

public enum SkeletonFileType {
    JAVA(SkeletonConstant.JAVA),
    RESOURCES(SkeletonConstant.RESOURCES),
    DOCKER(SkeletonConstant.DOCKER),
    ROOT(SkeletonConstant.ROOT);

    private String value;

    private SkeletonFileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SkeletonFileType fromString(String value) {
        for (SkeletonFileType type : SkeletonFileType.values()) {
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