package cn.springcloud.codegen.component.generator.zipkin;

import java.util.HashMap;
import java.util.Map;
import cn.springcloud.codegen.engine.context.SkeletonContext;
import cn.springcloud.codegen.engine.generator.SkeletonFileGenerator;
import cn.springcloud.codegen.engine.property.SkeletonProperties;
import cn.springcloud.codegen.engine.util.SkeletonUtil;
import cn.springcloud.codegen.engine.util.StringUtil;

public class ZipkinPomXmlGenerator extends SkeletonFileGenerator {

	public ZipkinPomXmlGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) {
		super(skeletonContext.clone("zipkin", ZipkinPomXmlGenerator.class), skeletonProperties);
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
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("springCloudVersion", skeletonProperties.getString("springCloudVersion"));
		map.put("javaVersion", skeletonProperties.getString("javaVersion"));
		map.put("pomGroupId", skeletonProperties.getString("pomGroupId"));
		map.put("pomArtifactId", skeletonProperties.getString("pomArtifactId")+"-"+getSkeletonContext().getProjectType());
		map.put("pomName", skeletonProperties.getString("pomArtifactId")+" "+StringUtil.firstLetterToLower(getSkeletonContext().getProjectType())+"Application");
		map.put("mainClass", SkeletonUtil.getBasePackagePath(getSkeletonContext().getProjectType(), skeletonProperties)+"."+StringUtil.firstLetterToLower(getSkeletonContext().getProjectType())+"Application");
		return null;
	}

}
