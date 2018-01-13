package cn.springcloud.codegen.component.generator.zipkin;

import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.engine.util.SkeletonUtil;
import com.nepxion.skeleton.engine.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

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
//		map.put("springCloudVersion", skeletonProperties.getString("springCloudVersion"));
		map.put("springBootVersion", skeletonProperties.getString("springBootVersion"));
		map.put("javaVersion", skeletonProperties.getString("javaVersion"));
		map.put("pomGroupId", skeletonProperties.getString("pomGroupId"));
		map.put("pomArtifactId", skeletonProperties.getString("pomArtifactId")+"-"+getSkeletonContext().getProjectType());
		map.put("pomName", skeletonProperties.getString("pomArtifactId")+" "+ StringUtil.firstLetterToLower(getSkeletonContext().getProjectType())+"Application");
		map.put("mainClass", SkeletonUtil.getBasePackagePath(getSkeletonContext().getProjectType(), skeletonProperties)+"."+StringUtil.firstLetterToLower(getSkeletonContext().getProjectType())+"Application");
		return map;
	}

}
