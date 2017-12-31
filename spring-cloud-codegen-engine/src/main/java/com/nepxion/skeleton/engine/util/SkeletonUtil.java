package com.nepxion.skeleton.engine.util;

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

import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.exception.SkeletonException;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

public class SkeletonUtil {
    public static String getOutputPath(String generatePath, SkeletonProperties skeletonProperties) {
        return getOutputPath(generatePath, null, skeletonProperties);
    }

    public static String getOutputPath(String generatePath, String projectType, SkeletonProperties skeletonProperties) {
        return formatGeneratePath(generatePath) + (StringUtils.isNotEmpty(projectType) ? getBaseDirectoryName(projectType, skeletonProperties) + "/" : "");
    }

    public static String getBaseDirectoryName(SkeletonProperties skeletonProperties) {
        return getBaseDirectoryName(null, skeletonProperties);
    }

    public static String getBaseDirectoryName(String projectType, SkeletonProperties skeletonProperties) {
        return skeletonProperties.getString(SkeletonConstant.MODULE_NAME) + (StringUtils.isNotEmpty(projectType) ? "-" + projectType : "");
    }

    public static String getBasePackagePath(SkeletonProperties skeletonProperties) {
        return getBasePackagePath(null, skeletonProperties);
    }

    public static String getBasePackagePath(String projectType, SkeletonProperties skeletonProperties) {
        String moduleName = skeletonProperties.getString(SkeletonConstant.MODULE_NAME);

        return skeletonProperties.getString(SkeletonConstant.BASE_PACKAGE) + "." + formatModuleName(moduleName) + (StringUtils.isNotEmpty(projectType) ? "." + projectType : "");
    }

    public static String getCanonicalFileName(String fileName, SkeletonProperties skeletonProperties) {
        return fileName + "-" + getBaseDirectoryName(skeletonProperties);
    }

    public static String getCanonicalPath(String generatePath, String fileName, SkeletonProperties skeletonProperties) {
        return formatGeneratePath(generatePath) + getCanonicalFileName(fileName, skeletonProperties);
    }

    public static String formatGeneratePath(Class<?> generatorClass, String reducedPath) {
        StringBuilder sb = new StringBuilder();
        sb.append(generatorClass.getCanonicalName());

        String path = sb.toString();
        path = path.substring(0, path.lastIndexOf("."));
        path = path.replace(".", SkeletonConstant.FILE_SEPARATOR);
        path += SkeletonConstant.FILE_SEPARATOR;

        if (StringUtils.isNotEmpty(reducedPath)) {
            try {
                int reducedPathLength = reducedPath.length();
                int pathLength = path.length();
                if (reducedPathLength < pathLength) {
                    return path.substring(reducedPathLength, pathLength - 1);
                } else {
                    return "";
                }
            } catch (Exception e) {
                throw new SkeletonException("Path=[" + path + "] doesn't contain reducedPath=[" + reducedPath + "]");
            }
        }

        return path;
    }

    public static String formatGeneratePath(String generatePath) {
        StringBuilder sb = new StringBuilder();
        sb.append(generatePath);

        String path = sb.toString();
        path = path.replace("\\", SkeletonConstant.FILE_SEPARATOR);
        if (!path.endsWith(SkeletonConstant.FILE_SEPARATOR)) {
            path += SkeletonConstant.FILE_SEPARATOR;
        }

        return path;
    }

    public static String formatModuleName(String moduleName) {
        StringBuilder sb = new StringBuilder();

        String[] splittedModuleNames = moduleName.split("-");
        for (String splittedModuleName : splittedModuleNames) {
            sb.append(splittedModuleName.trim()).append(".");
        }

        String formattedModuleName = sb.toString();

        return formattedModuleName.substring(0, formattedModuleName.lastIndexOf("."));
    }

    public static String getTempGeneratePath() {
        String tempGeneratePath = formatGeneratePath(System.getProperty("java.io.tmpdir")) + SkeletonConstant.SKELETON;

        return tempGeneratePath;
    }
}