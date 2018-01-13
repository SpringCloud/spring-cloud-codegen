package cn.springcloud.codegen.component.generator.zipkin.java;


import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.generator.SkeletonJavaGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: xxp
 * @date: 2017/12/22
 * @time: 23:58
 * @description : this is zipkin
 */
public class ZipkinJavaClassGenerator extends SkeletonJavaGenerator {

    public ZipkinJavaClassGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) {
        super(skeletonContext.clone("zipkin", ZipkinJavaClassGenerator.class), skeletonProperties);
    }

    @Override
    protected String getClassName() {
        return "ZipkinApplication";
    }

    @Override
    protected boolean isMainCode() {
        return true;
    }

    @Override
    protected String getTemplateName() {
        return "ZipkinApplication.java.template";
    }

    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put(SkeletonConstant.PACKAGE, getPackage());

        return dataModel;
    }
}
