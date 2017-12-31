package cn.springcloud.codegen.generator.springboot;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import com.nepxion.skeleton.engine.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

import java.util.HashMap;
import java.util.Map;

public class PomXmlGenerator extends SkeletonFileGenerator {
    /**
     * 构造方法
     * @param generatePath 创建文件的顶级路径
     * @param projectType 工程类型
     * @param prefixTemplatePath 前置模板路径，例如template
     * @param reducedTemplatePath 模板路径缩减，考虑到模板路径和类路径必须一致，会导致路径太长，可以缩减掉一部分
     * @param skeletonProperties 全局配置文件对象
     */
    public PomXmlGenerator(String generatePath, String projectType, String prefixTemplatePath, String reducedTemplatePath, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, PomXmlGenerator.class, skeletonProperties);
    }

    /**
     * 构造方法
     * @param generatePath 创建文件的顶级路径
     * @param projectType 工程类型
     * @param baseTemplatePath 模板文件的顶级路径
     * @param fileType 创建的文件类型
     * @param skeletonProperties 全局配置文件对象
     */
    /*public PomXmlGenerator(String generatePath, String projectType, String baseTemplatePath, SkeletonFileType fileType, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, baseTemplatePath, fileType, skeletonProperties);
    }*/

    /**
     * 设置文件名
     */
    @Override
    protected String getFileName() {
        return "pom.xml";
    }

    /**
     * 设置模板名
     */
    @Override
    protected String getTemplateName() {
        return "pom.xml.template";
    }

    /**
     * 设置文件的输出路径
     */
    @Override
    protected String getOutputPath() {
        return super.getOutputPath();
    }

    /**
     * 设置文件创建的所依赖数据模型，主要做动态变量到原型模板的替换（任何文本的替换都支持）
     */
    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("pomGroupId", skeletonProperties.getString("pomGroupId"));
        dataModel.put("pomArtifactId", skeletonProperties.getString("pomArtifactId"));
        dataModel.put("pomName", skeletonProperties.getString("pomName"));
        dataModel.put("pomVersion", skeletonProperties.getString("pomVersion"));
        dataModel.put("springCloudVersion", skeletonProperties.getString("springCloudVersion"));
        dataModel.put("springBootVersion", skeletonProperties.getString("springBootVersion"));
        dataModel.put("javaVersion", skeletonProperties.getString("javaVersion"));

        return dataModel;
    }
}