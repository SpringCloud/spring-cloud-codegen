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
 * @date: 2017/12/23
 * @time: 0:02
 * @description : this is gateway server
 */
public class ZuulJavaClassGenerator  extends SkeletonJavaGenerator{

    public ZuulJavaClassGenerator(String generatePath, String projectType, String prefixTemplatePath, String reducedTemplatePath, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, ZuulJavaClassGenerator.class, skeletonProperties);
    }

    @Override
    protected String getClassName() {
        return "application.properties";
    }

    @Override
    protected boolean isMainCode() {
        return true;
    }

    @Override
    protected String getTemplateName() {
        return "ZuulApplication.java.template";
    }

    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put(SkeletonConstant.PACKAGE, getPackage());

        return dataModel;
    }
}
