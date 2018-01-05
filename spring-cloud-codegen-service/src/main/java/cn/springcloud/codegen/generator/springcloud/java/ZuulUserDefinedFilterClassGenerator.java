package cn.springcloud.codegen.generator.springcloud.java;

import java.util.HashMap;
import java.util.Map;

import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.generator.SkeletonJavaGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

/**
 * Created with IntelliJ IDEA.
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
