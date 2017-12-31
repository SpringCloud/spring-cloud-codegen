package com.nepxion.skeleton.engine.context;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import org.apache.commons.lang3.StringUtils;

import com.nepxion.skeleton.engine.config.SkeletonConfig;
import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.entity.SkeletonFileType;
import com.nepxion.skeleton.engine.util.SkeletonUtil;

public class SkeletonContext {
    private String projectType;

    private String prefixTemplatePath;
    private String reducedTemplatePath;
    private Class<?> generatorClass;

    private String baseTemplatePath;
    private SkeletonFileType fileType;

    private SkeletonConfig config;

    public SkeletonContext(String projectType, String prefixTemplatePath, String reducedTemplatePath, Class<?> generatorClass) {
        this.projectType = projectType;
        this.prefixTemplatePath = prefixTemplatePath;
        this.reducedTemplatePath = reducedTemplatePath;
        this.generatorClass = generatorClass;
        this.config = new SkeletonConfig(generateTemplatePath());
    }

    public SkeletonContext(String projectType, String baseTemplatePath, SkeletonFileType fileType) {
        this.projectType = projectType;
        this.baseTemplatePath = baseTemplatePath;
        this.fileType = fileType;
        this.config = new SkeletonConfig(generateTemplatePath());
    }

    public String getProjectType() {
        return projectType;
    }

    public String getPrefixTemplatePath() {
        return prefixTemplatePath;
    }

    public String getReducedTemplatePath() {
        return reducedTemplatePath;
    }

    public Class<?> getGeneratorClass() {
        return generatorClass;
    }

    public String getBaseTemplatePath() {
        return baseTemplatePath;
    }

    public SkeletonFileType getFileType() {
        return fileType;
    }

    public SkeletonConfig getConfig() {
        return config;
    }

    private String generateTemplatePath() {
        if (generatorClass != null) {
            return SkeletonConstant.FILE_SEPARATOR + (StringUtils.isNotEmpty(prefixTemplatePath) ? prefixTemplatePath + SkeletonConstant.FILE_SEPARATOR : "") + SkeletonUtil.formatGeneratePath(generatorClass, reducedTemplatePath);
        }

        return SkeletonConstant.FILE_SEPARATOR + SkeletonUtil.formatGeneratePath(baseTemplatePath) + (StringUtils.isNotEmpty(projectType) ? projectType : "") + SkeletonConstant.FILE_SEPARATOR + fileType;
    }
}