package cn.springcloud.codegen.generator.springcloud.java;

import cn.springcloud.codegen.engine.constant.SkeletonConstant;
import cn.springcloud.codegen.engine.generator.SkeletonJavaGenerator;
import cn.springcloud.codegen.engine.property.SkeletonProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zzf
 * @date: 2018/1/1
 * @time: 22:36
 * @description : 用户自定义网关Filter
 */
public class ZuulUserDefinedFilterClassGenerator extends SkeletonJavaGenerator {

    public ZuulUserDefinedFilterClassGenerator(String generatePath, String projectType, String prefixTemplatePath, String reducedTemplatePath, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, ZuulUserDefinedFilterClassGenerator.class, skeletonProperties);
    }

    @Override
    protected String getClassName() {
        return "DefaultZuulFilter";
    }

    @Override
    protected boolean isMainCode() {
        return true;
    }

    @Override
    protected String getTemplateName() {
        return "DefaultZuulFilterClass.java.template";
    }

    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put(SkeletonConstant.PACKAGE, getPackage());

        return dataModel;
    }
}
