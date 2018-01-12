package cn.springcloud.codegen.component.generator.zipkin.java;

import cn.springcloud.codegen.engine.constant.SkeletonConstant;
import cn.springcloud.codegen.engine.context.SkeletonContext;
import cn.springcloud.codegen.engine.generator.SkeletonJavaGenerator;
import cn.springcloud.codegen.engine.property.SkeletonProperties;

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
