package cn.springcloud.codegen.component.generator.provider.java;

import cn.springcloud.codegen.component.generator.eureka.java.EurekaJavaClassGenerator;
import cn.springcloud.codegen.engine.constant.SkeletonConstant;
import cn.springcloud.codegen.engine.context.SkeletonContext;
import cn.springcloud.codegen.engine.generator.SkeletonJavaGenerator;
import cn.springcloud.codegen.engine.property.SkeletonProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zzf
 * @date: 2018/1/7
 * @time: 1:18
 * @description : do some thing
 */
public class ProviderJavaClassGenerator extends SkeletonJavaGenerator{


    public ProviderJavaClassGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) {
        super(skeletonContext.clone("provider", ProviderJavaClassGenerator.class), skeletonProperties);
    }

    @Override
    protected String getClassName() {
        return "ProviderApplication";
    }

    @Override
    protected boolean isMainCode() {
        return true;
    }

    @Override
    protected String getTemplateName() {
        return "ProviderApplication.java.template";
    }

    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put(SkeletonConstant.PACKAGE, getPackage());

        return dataModel;
    }
}
