package cn.springcloud.codegen.generator.springboot.resources;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import cn.springcloud.codegen.engine.constant.SkeletonConstant ;
import cn.springcloud.codegen.engine.generator.SkeletonFileGenerator;
import cn.springcloud.codegen.engine.property.SkeletonProperties;

import java.util.HashMap;
import java.util.Map;

public class ResGenerator extends SkeletonFileGenerator {
    /**
     * 构造方法
     * @param generatePath 创建文件的顶级路径
     * @param projectType 工程类型
     * @param prefixTemplatePath 前置模板路径，例如template
     * @param reducedTemplatePath 模板路径缩减，考虑到模板路径和类路径必须一致，会导致路径太长，可以缩减掉一部分
     * @param skeletonProperties 全局配置文件对象
     */
    public ResGenerator(String generatePath, String projectType, String prefixTemplatePath, String reducedTemplatePath, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, ResGenerator.class, skeletonProperties);
    }

    /**
     * 构造方法
     * @param generatePath 创建文件的顶级路径
     * @param projectType 工程类型
     * @param baseTemplatePath 模板文件的顶级路径
     * @param fileType 创建的文件类型
     * @param skeletonProperties 全局配置文件对象
     */
    /*public ResGenerator(String generatePath, String projectType, String baseTemplatePath, SkeletonFileType fileType, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, baseTemplatePath, fileType, skeletonProperties);
    }*/

    /**
     * 设置文件名
     */
    @Override
    protected String getFileName() {
        return "application.properties";
    }

    /**
     * 设置模板名
     */
    @Override
    protected String getTemplateName() {
        return "application.properties.template";
    }

    /**
     * 设置文件的输出路径
     */
    @Override
    protected String getOutputPath() {
        return super.getOutputPath() + SkeletonConstant.MAIN_RESOURCES_FILE_PATH;
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