package cn.springcloud.codegen.generator.springcloud.resources;

import java.util.HashMap;
import java.util.Map;

import com.nepxion.skeleton.engine.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

/**
 * Created with IntelliJ IDEA.
 * @author: zzf
 * @date: 2017/12/22
 * @time: 23:59
 * @description : do some thing
 */
public class EurekaResourcesGenerator extends SkeletonFileGenerator {

    public EurekaResourcesGenerator(String generatePath, String projectType, String prefixTemplatePath, String reducedTemplatePath, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, EurekaResourcesGenerator.class, skeletonProperties);
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
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("serviceName", skeletonProperties.getString("eurekaApplicationName") + "-" + getSkeletonContext().getProjectType());
        dataModel.put("port", skeletonProperties.getString("eurekaPort"));
        dataModel.put("eurekaUrl", skeletonProperties.getString("eurekaUrl"));

        return dataModel;
    }
}
