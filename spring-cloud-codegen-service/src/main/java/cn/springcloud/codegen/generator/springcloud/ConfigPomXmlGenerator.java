package cn.springcloud.codegen.generator.springcloud;

import java.util.HashMap;
import java.util.Map;

import com.nepxion.skeleton.engine.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

/**
 * Created with IntelliJ IDEA.
 * @author: zzf
 * @date: 2017/12/23
 * @time: 8:30
 * @description : do some thing
 */
public class ConfigPomXmlGenerator extends SkeletonFileGenerator {

    public ConfigPomXmlGenerator(String generatePath, String projectType, String prefixTemplatePath, String reducedTemplatePath, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, ConfigPomXmlGenerator.class, skeletonProperties);
    }

    @Override
    protected String getFileName() {
        return "pom.xml";
    }

    @Override
    protected String getTemplateName() {
        return "pom.xml.template";
    }

    @Override
    protected String getOutputPath() {
        return super.getOutputPath();
    }

    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("pomGroupId", skeletonProperties.getString("configPomGroupId"));
        dataModel.put("pomArtifactId", skeletonProperties.getString("configPomArtifactId"));
        dataModel.put("pomName", skeletonProperties.getString("configPomName"));
        dataModel.put("pomVersion", skeletonProperties.getString("configPomVersion"));
        dataModel.put("springCloudVersion", skeletonProperties.getString("springCloudVersion"));
        dataModel.put("springBootVersion", skeletonProperties.getString("springBootVersion"));
        dataModel.put("javaVersion", skeletonProperties.getString("javaVersion"));

        return dataModel;
    }
}
