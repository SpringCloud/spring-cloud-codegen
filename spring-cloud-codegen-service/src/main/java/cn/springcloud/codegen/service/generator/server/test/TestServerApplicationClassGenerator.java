package cn.springcloud.codegen.service.generator.server.test;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import com.nepxion.skeleton.constant.SkeletonConstant;
import com.nepxion.skeleton.generator.SkeletonJavaGenerator;
import com.nepxion.skeleton.property.SkeletonProperties;

import java.util.HashMap;
import java.util.Map;

public class TestServerApplicationClassGenerator extends SkeletonJavaGenerator {
    /**
     * 构造方法
     * @param generatePath 创建文件的顶级路径
     * @param projectType 工程类型
     * @param prefixTemplateDirectory 前置模板目录名，例如template
     * @param reducedTemplateDirectory 模板目录缩减，考虑到模板目录和类目录必须一致，会导致目录目录太长，可以缩减掉一部分
     * @param skeletonProperties 全局配置文件对象
     */
    public TestServerApplicationClassGenerator(String generatePath, String projectType, String prefixTemplateDirectory, String reducedTemplateDirectory, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplateDirectory, reducedTemplateDirectory, TestServerApplicationClassGenerator.class, skeletonProperties);
    }

    /**
     * 构造方法
     * @param generatePath 创建文件的顶级路径
     * @param projectType 工程类型
     * @param baseTemplatePath 模板文件的等级路径
     * @param skeletonProperties 全局配置文件对象
     */
    /*public TestServerApplicationClassGenerator(String generatePath, String projectType, String baseTemplatePath, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, baseTemplatePath, skeletonProperties);
    }*/

    /**
     * 设置Java类的包路径，如果没特殊处理，则按照默认顶级包路径来处理，不需要Override该方法
     */
    /*@Override
    protected String getPackage() {
        return super.getPackage() + "." + "abc";
    }*/

    /**
     * 设置Java类名
     */
    @Override
    protected String getClassName() {
        return "ServerApplication";
    }

    /**
     * 设置模板名
     */
    @Override
    protected String getTemplateName() {
        return "ServerApplication.java.template";
    }

    /**
     * 设置Java类的输出路径，如果没特殊处理，则按照默认输出路径来处理，不需要Override该方法
     */
    /*@Override
    protected String getOutputPath() {
        return super.getOutputPath() + "/" + "xyz";
    }*/

    /**
     * 设置Java类到main目录下，还是在test目录下
     */
    @Override
    protected boolean isMainCode() {
        return false;
    }

    /**
     * 设置Java类创建的所依赖数据模型，主要做动态变量到原型模板的替换（任何文本的替换都支持）
     */
    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        // 注意：根据freemarker的规范，dataModel中的key似乎只能支持字母和数字，不支持符号，例如eureka.Enabled，eureka-Enabled都会抛错
        dataModel.put(SkeletonConstant.PACKAGE, getPackage());
        dataModel.put("eurekaEnabled", skeletonProperties.getString("eurekaEnabled"));

        return dataModel;
    }
}