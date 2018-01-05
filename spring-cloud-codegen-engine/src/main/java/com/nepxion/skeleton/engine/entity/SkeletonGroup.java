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

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SkeletonGroup implements Serializable {
    private static final long serialVersionUID = -7892454279861098493L;

    private String key;
    private String label;
    private String description;
    private SkeletonGroupType type = SkeletonGroupType.MIX_GROUP;
    private SkeletonGroupLayoutType layoutType = SkeletonGroupLayoutType.VERTICAL;
    private boolean titledBorder = true;

    private List<SkeletonEntity> entityList;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SkeletonGroupType getType() {
        return type;
    }

    public void setType(SkeletonGroupType type) {
        this.type = type;
    }

    public SkeletonGroupLayoutType getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(SkeletonGroupLayoutType layoutType) {
        this.layoutType = layoutType;
    }

    public List<SkeletonEntity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<SkeletonEntity> entityList) {
        this.entityList = entityList;
    }

    public boolean isTitledBorder() {
        return titledBorder;
    }

    public void setTitledBorder(boolean titledBorder) {
        this.titledBorder = titledBorder;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object object) {
        return EqualsBuilder.reflectionEquals(this, object);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}