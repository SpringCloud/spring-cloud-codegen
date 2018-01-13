package cn.springcloud.codegen.component.generator.zuul.java;


import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.generator.SkeletonJavaGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vincent.
 * @createdOn 2018/01/12 00:08
 */
public class ErrorFilterJavaClassGenerator extends SkeletonJavaGenerator {
    public ErrorFilterJavaClassGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) {
        super(skeletonContext.clone("zuul", ErrorFilterJavaClassGenerator.class), skeletonProperties);
    }

    @Override
    protected String getPackage(){
        return super.getPackage() + ".filter";
    }

    @Override
    protected String getClassName() {
        return "ErrorFilter";
    }

    @Override
    protected boolean isMainCode() {
        return true;
    }

    @Override
    protected String getTemplateName() {
        return "ZuulFilter.java.template";
    }

    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put(SkeletonConstant.PACKAGE, getPackage());
        dataModel.put("className", getClassName());
        dataModel.put("filterType", "error");
        dataModel.put("explanation", "处理请求时发生错误时被调用");
        return dataModel;
    }
}
