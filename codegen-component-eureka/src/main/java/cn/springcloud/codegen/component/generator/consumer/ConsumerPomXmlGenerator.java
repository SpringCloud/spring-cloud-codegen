package cn.springcloud.codegen.component.generator.consumer;

import cn.springcloud.codegen.engine.context.SkeletonContext;
import cn.springcloud.codegen.engine.generator.SkeletonFileGenerator;
import cn.springcloud.codegen.engine.property.SkeletonProperties;
import cn.springcloud.codegen.engine.util.SkeletonUtil;
import cn.springcloud.codegen.engine.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zzf
 * @date: 2018/1/7
 * @time: 1:10
 * @description : do some thing
 */
public class ConsumerPomXmlGenerator  extends SkeletonFileGenerator{

    public ConsumerPomXmlGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) {
        super(skeletonContext.clone("consumer", ConsumerPomXmlGenerator.class), skeletonProperties);
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
    protected Object getDataModel() {

        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("parentPomArtifactId", skeletonProperties.getString("pomArtifactId"));
        dataModel.put("pomGroupId", skeletonProperties.getString("pomGroupId"));
        dataModel.put("pomArtifactId", skeletonProperties.getString("pomArtifactId") + "-" + getSkeletonContext().getProjectType());
        dataModel.put("pomName", skeletonProperties.getString("pomName") + " " + StringUtil.firstLetterToUpper(getSkeletonContext().getProjectType()));
        dataModel.put("pomVersion", skeletonProperties.getString("pomVersion"));
        dataModel.put("javaImageVersion", skeletonProperties.getString("javaImageVersion"));
        dataModel.put("mainClass", SkeletonUtil.getBasePackagePath(getSkeletonContext().getProjectType(), skeletonProperties) + "." + StringUtil.firstLetterToUpper(getSkeletonContext().getProjectType()) + "Application");
        dataModel.put("clientHystrixEnabled", skeletonProperties.getString("clientHystrixEnabled"));

        return dataModel;
    }
}
