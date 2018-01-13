package cn.springcloud.codegen.component.generator.springmvc;

import java.util.HashMap;
import java.util.Map;


import cn.springcloud.codegen.engine.context.SkeletonContext;
import cn.springcloud.codegen.engine.generator.SkeletonFileGenerator;
import cn.springcloud.codegen.engine.property.SkeletonProperties;
import cn.springcloud.codegen.engine.util.SkeletonUtil;
import cn.springcloud.codegen.engine.util.StringUtil;

public class SpringBootSpringMvcPomXmlGenerator extends SkeletonFileGenerator {

	public SpringBootSpringMvcPomXmlGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) {
		super(skeletonContext.clone("springBootSpringMvc", SpringBootSpringMvcPomXmlGenerator.class), skeletonProperties);
		
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
	Map<String,Object> dataModel=new  HashMap<String,Object>();
	  dataModel.put("springBootVersion", skeletonProperties.getString("springBootVersion"));
      dataModel.put("javaVersion", skeletonProperties.getString("javaVersion"));
      dataModel.put("pomGroupId", skeletonProperties.getString("pomGroupId"));
      dataModel.put("pomArtifactId", skeletonProperties.getString("pomArtifactId") + "-" + getSkeletonContext().getProjectType());
      dataModel.put("pomName", skeletonProperties.getString("pomArtifactId") + " " + StringUtil.firstLetterToUpper(getSkeletonContext().getProjectType()));
      dataModel.put("mainClass", SkeletonUtil.getBasePackagePath(getSkeletonContext().getProjectType(), skeletonProperties) + "." + StringUtil.firstLetterToUpper(getSkeletonContext().getProjectType()) + "Application");
		return dataModel;
	}

}
