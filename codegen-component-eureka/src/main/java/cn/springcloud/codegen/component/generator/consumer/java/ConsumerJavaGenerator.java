package cn.springcloud.codegen.component.generator.consumer.java;

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
 * @date: 2018/1/6
 * @time: 16:52
 * @description : 服务消费者
 */
public class ConsumerJavaGenerator extends SkeletonJavaGenerator {

    public ConsumerJavaGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) {
        super(skeletonContext.clone("consumer", EurekaJavaClassGenerator.class), skeletonProperties);
    }

    @Override
    protected String getClassName() {
        return "ConsumerApplication";
    }

    @Override
    protected boolean isMainCode() {
        return true;
    }

    @Override
    protected String getTemplateName() {
        return "Consumer.java.template";
    }

    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put(SkeletonConstant.PACKAGE, getPackage());
        dataModel.put("clientHystrixEnabled", skeletonProperties.getString("clientHystrixEnabled"));

        return dataModel;
    }
}
