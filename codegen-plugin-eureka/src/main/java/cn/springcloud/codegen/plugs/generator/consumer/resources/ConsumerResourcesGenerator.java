package cn.springcloud.codegen.plugs.generator.consumer.resources;

import cn.springcloud.codegen.engine.constant.SkeletonConstant;
import cn.springcloud.codegen.engine.context.SkeletonContext;
import cn.springcloud.codegen.engine.generator.SkeletonFileGenerator;
import cn.springcloud.codegen.engine.property.SkeletonProperties;
import cn.springcloud.codegen.plugs.generator.eureka.resources.EurekaResourcesGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zzf
 * @date: 2018/1/7
 * @time: 1:04
 * @description : 客户端资源文件的生成
 */
public class ConsumerResourcesGenerator extends SkeletonFileGenerator {

    public ConsumerResourcesGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) {
        super(skeletonContext.clone("consumer", ConsumerResourcesGenerator.class), skeletonProperties);
    }

    @Override
    protected String getFileName() {
        return "application.properties";
    }

    @Override
    protected String getTemplateName() {
        return "application.properties.template";
    }

    @Override
    protected String getOutputPath() {
        return super.getOutputPath() + SkeletonConstant.MAIN_RESOURCES_FILE_PATH;
    }

    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("serviceName", skeletonProperties.getString("serviceName") + "-" + getSkeletonContext().getProjectType());
        dataModel.put("clusterName", skeletonProperties.getString("serviceName") + "-server");
        dataModel.put("port", skeletonProperties.getString("consumerPort"));
        dataModel.put("registryUrl", skeletonProperties.getString("registryUrl"));

        return dataModel;
    }
}
