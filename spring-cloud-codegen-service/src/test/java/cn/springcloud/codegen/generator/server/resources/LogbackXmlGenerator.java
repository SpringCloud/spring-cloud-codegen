package cn.springcloud.codegen.generator.server.resources;

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

import com.nepxion.skeleton.constant.SkeletonConstant;
import com.nepxion.skeleton.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.property.SkeletonProperties;

public class LogbackXmlGenerator extends SkeletonFileGenerator {
    /**
     * 构造方法
     * @param generatePath 创建文件的顶级路径
     * @param projectType 工程类型
     * @param prefixTemplateDirectory 前置模板目录名，例如template
     * @param reducedTemplateDirectory 模板目录缩减，考虑到模板目录和类目录必须一致，会导致目录目录太长，可以缩减掉一部分
     * @param skeletonProperties 全局配置文件对象
     */
    public LogbackXmlGenerator(String generatePath, String projectType, String prefixTemplateDirectory, String reducedTemplateDirectory, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplateDirectory, reducedTemplateDirectory, LogbackXmlGenerator.class, skeletonProperties);
    }

    /**
     * 构造方法
     * @param generatePath 创建文件的顶级路径
     * @param projectType 工程类型
     * @param baseTemplatePath 模板文件的等级路径
     * @param fileType 创建的文件类型
     * @param skeletonProperties 全局配置文件对象
     */
    /*public LogbackXmlGenerator(String generatePath, String projectType, String baseTemplatePath, SkeletonFileType fileType, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, baseTemplatePath, fileType, skeletonProperties);
    }*/

    /**
     * 设置文件名
     */
    @Override
    protected String getFileName() {
        return "logback.xml";
    }

    /**
     * 设置模板名
     */
    @Override
    protected String getTemplateName() {
        return "logback.xml.template";
    }

    /**
     * 设置文件的输出路径
     */
    @Override
    protected String getOutputPath() {
        return super.getOutputPath() + SkeletonConstant.TEST_RESOURCES_FILE_PATH;
    }

    /**
     * 设置文件创建的所依赖数据模型，主要做动态变量到原型模板的替换（任何文本的替换都支持）
     */
    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        // 注意：根据freemarker的规范，dataModel中的key似乎只能支持字母和数字，不支持符号，例如service.Name，service-Name都会抛错
        dataModel.put("serviceName", skeletonProperties.getString("serviceName"));
        dataModel.put("port", skeletonProperties.getString("port"));
        dataModel.put("eurekaUrl", skeletonProperties.getString("eurekaUrl"));

        return dataModel;
    }
}