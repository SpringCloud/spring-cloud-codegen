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
public class ZuulPomXmlGenrator extends SkeletonFileGenerator{

    public ZuulPomXmlGenrator(String generatePath, String projectType, String prefixTemplatePath, String reducedTemplatePath, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, ZuulPomXmlGenrator.class, skeletonProperties);
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
        dataModel.put("pomGroupId", skeletonProperties.getString("zuulPomGroupId"));
        dataModel.put("pomArtifactId", skeletonProperties.getString("zuulPomArtifactId"));
        dataModel.put("pomName", skeletonProperties.getString("zuulPomName"));
        dataModel.put("pomVersion", skeletonProperties.getString("zuulPomVersion"));
        dataModel.put("springCloudVersion", skeletonProperties.getString("springCloudVersion"));
        dataModel.put("springBootVersion", skeletonProperties.getString("springBootVersion"));
        dataModel.put("javaVersion", skeletonProperties.getString("javaVersion"));

        return dataModel;
    }


}
