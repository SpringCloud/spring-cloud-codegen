package cn.springcloud.codegen.component.generator.eureka;

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
 * @date: 2017/12/23
 * @time: 8:30
 * @description : pom 文件的生成
 */
public class EurekaPomXmlGenerator extends SkeletonFileGenerator{

    public EurekaPomXmlGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) {
        super(skeletonContext.clone("eureka", EurekaPomXmlGenerator.class), skeletonProperties);
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

        dataModel.put("springBootVersion", skeletonProperties.getString("springBootVersion"));
//        dataModel.put("springCloudVersion", skeletonProperties.getString("springCloudVersion"));
        dataModel.put("javaVersion", skeletonProperties.getString("javaVersion"));

        dataModel.put("pomGroupId", skeletonProperties.getString("pomGroupId"));
        dataModel.put("pomArtifactId", skeletonProperties.getString("pomArtifactId") + "-" + getSkeletonContext().getProjectType());
        dataModel.put("pomName", skeletonProperties.getString("pomArtifactId") + " " + StringUtil.firstLetterToUpper(getSkeletonContext().getProjectType()));
//        dataModel.put("pomVersion", skeletonProperties.getString("pomVersion"));

//        dataModel.put("javaImageVersion", skeletonProperties.getString("javaImageVersion"));
        dataModel.put("mainClass", SkeletonUtil.getBasePackagePath(getSkeletonContext().getProjectType(), skeletonProperties) + "." + StringUtil.firstLetterToUpper(getSkeletonContext().getProjectType()) + "Application");
        return dataModel;
    }
}
