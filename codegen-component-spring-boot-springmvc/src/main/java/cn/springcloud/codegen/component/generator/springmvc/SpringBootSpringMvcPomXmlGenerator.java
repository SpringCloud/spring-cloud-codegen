package cn.springcloud.codegen.component.generator.springmvc;

import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.engine.util.SkeletonUtil;
import com.nepxion.skeleton.engine.util.StringUtil;

import java.util.HashMap;
import java.util.Map;


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
