package cn.springcloud.codegen.component.generator.zuul.java;


import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.generator.SkeletonJavaGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vincent.
 * @createdOn 2018/01/08 22:42
 */
public class ZuulJavaClassGenerator extends SkeletonJavaGenerator {

    public ZuulJavaClassGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) {
        super(skeletonContext.clone("zuul", ZuulJavaClassGenerator.class), skeletonProperties);
    }

    @Override
    protected String getClassName() {
        return "ZuulApplication";
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
