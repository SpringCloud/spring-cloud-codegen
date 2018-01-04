package cn.springcloud.codegen.generator.springcloud;

import cn.springcloud.codegen.engine.generator.SkeletonFileGenerator;
import cn.springcloud.codegen.engine.property.SkeletonProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zzf
 * @date: 2017/12/23
 * @time: 8:30
 * @description : do some thing
 */
public class EurekaPomXmlGenerator extends SkeletonFileGenerator{

    public EurekaPomXmlGenerator(String generatePath, String projectType, String prefixTemplatePath, String reducedTemplatePath, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, EurekaPomXmlGenerator.class, skeletonProperties);
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
        dataModel.put("pomGroupId", skeletonProperties.getString("eurekaPomGroupId"));
        dataModel.put("pomArtifactId", skeletonProperties.getString("eurekaPomArtifactId"));
        dataModel.put("pomName", skeletonProperties.getString("eurekaPomName"));
        dataModel.put("pomVersion", skeletonProperties.getString("eurekaPomVersion"));
        dataModel.put("springCloudVersion", skeletonProperties.getString("springCloudVersion"));
        dataModel.put("springBootVersion", skeletonProperties.getString("springBootVersion"));
        dataModel.put("javaVersion", skeletonProperties.getString("javaVersion"));

        return dataModel;
    }
}
