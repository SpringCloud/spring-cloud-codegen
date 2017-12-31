package com.nepxion.skeleton.engine.generator;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.entity.SkeletonFileType;
import com.nepxion.skeleton.engine.exception.SkeletonException;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.engine.util.SkeletonUtil;

public abstract class SkeletonJavaGenerator extends AbstractSkeletonGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(SkeletonJavaGenerator.class);

    protected String defaultBasePackage;
    protected String defaultOutputPath;

    public SkeletonJavaGenerator(String generatePath, String projectType, String prefixTemplatePath, String reducedTemplatePath, Class<?> generatorClass, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, generatorClass, skeletonProperties);

        defaultBasePackage = SkeletonUtil.getBasePackagePath(projectType, skeletonProperties);
        defaultOutputPath = SkeletonUtil.getOutputPath(generatePath, projectType, skeletonProperties);
    }

    public SkeletonJavaGenerator(String generatePath, String projectType, String baseTemplatePath, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, baseTemplatePath, SkeletonFileType.JAVA, skeletonProperties);

        defaultBasePackage = SkeletonUtil.getBasePackagePath(projectType, skeletonProperties);
        defaultOutputPath = SkeletonUtil.getOutputPath(generatePath, projectType, skeletonProperties);
    }

    public String getDefaultBasePackage() {
        return defaultBasePackage;
    }

    public String getDefaultOutputPath() {
        return defaultOutputPath;
    }

    public Map<String, Object> generateDataModel() {
        Map<String, Object> defaultDataModel = new HashMap<String, Object>();
        defaultDataModel.put(SkeletonConstant.TITLE, skeletonProperties.getString(SkeletonConstant.TITLE));
        defaultDataModel.put(SkeletonConstant.DESCRIPTION, skeletonProperties.getString(SkeletonConstant.DESCRIPTION));
        defaultDataModel.put(SkeletonConstant.COPYRIGHT, skeletonProperties.getString(SkeletonConstant.COPYRIGHT));
        defaultDataModel.put(SkeletonConstant.COMPANY, skeletonProperties.getString(SkeletonConstant.COMPANY));
        defaultDataModel.put(SkeletonConstant.AUTHOR, skeletonProperties.getString(SkeletonConstant.AUTHOR));
        defaultDataModel.put(SkeletonConstant.EMAIL, skeletonProperties.getString(SkeletonConstant.EMAIL));
        defaultDataModel.put(SkeletonConstant.VERSION, skeletonProperties.getString(SkeletonConstant.VERSION));

        return defaultDataModel;
    }

    @Override
    protected String getPath() throws SkeletonException {
        String className = null;
        String packagePath = null;
        String outputPath = null;
        boolean isMainCode = true;
        Object dataModel = null;
        try {
            className = getClassName();
            packagePath = getPackage();
            outputPath = getOutputPath();
            isMainCode = isMainCode();
            dataModel = getDataModel();
        } catch (Exception e) {
            throw new SkeletonException("Get parameters error", e);
        }

        String fullPath = SkeletonUtil.formatGeneratePath(outputPath) + (isMainCode ? SkeletonConstant.MAIN_JAVA_CODE_PATH : SkeletonConstant.TEST_JAVA_CODE_PATH) + packagePath.replace(".", SkeletonConstant.FILE_SEPARATOR) + SkeletonConstant.FILE_SEPARATOR + className + "." + SkeletonConstant.JAVA;

        LOG.info("--------------- Java Generator Information ---------------");
        LOG.info("Template Path : {}", getSkeletonContext().getConfig().getTemplatePath() + SkeletonConstant.FILE_SEPARATOR + getTemplateName());
        LOG.info("Output Path : {}", fullPath);
        LOG.info("Package : {}", packagePath);
        LOG.info("Data Model : {}", dataModel);
        LOG.info("----------------------------------------------------------");

        return fullPath;
    }

    protected String getPackage() {
        if (StringUtils.isEmpty(defaultBasePackage)) {
            throw new IllegalArgumentException("Default base package is null or empty");
        }

        return defaultBasePackage;
    }

    protected String getOutputPath() {
        if (StringUtils.isEmpty(defaultOutputPath)) {
            throw new IllegalArgumentException("Default output path is null or empty");
        }

        return defaultOutputPath;
    }

    protected abstract String getClassName();

    protected abstract boolean isMainCode();
}