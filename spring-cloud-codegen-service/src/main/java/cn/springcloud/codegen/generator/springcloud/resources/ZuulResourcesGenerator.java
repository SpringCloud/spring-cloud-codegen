package cn.springcloud.codegen.generator.springcloud.resources;

import java.util.HashMap;
import java.util.Map;

import com.nepxion.skeleton.engine.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

/**
 * Created with IntelliJ IDEA.
 * @author: zzf
 * @date: 2017/12/23
 * @time: 0:02
 * @description : 网关配置的生成
 */
public class ZuulResourcesGenerator extends SkeletonFileGenerator {

    public ZuulResourcesGenerator(String generatePath, String projectType, String prefixTemplatePath, String reducedTemplatePath, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, ZuulResourcesGenerator.class, skeletonProperties);
    }

    @Override
    protected String getFileName() {
        return "application";
    }

    @Override
    protected String getTemplateName() {
        return "application.properties";
    }

    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("serviceName", skeletonProperties.getString("zuulApplicationName") + "-" + getSkeletonContext().getProjectType());
        dataModel.put("port", skeletonProperties.getString("zuulPort"));
        dataModel.put("eurekaUrl", skeletonProperties.getString("eurekaUrl"));

        return dataModel;
    }
}
