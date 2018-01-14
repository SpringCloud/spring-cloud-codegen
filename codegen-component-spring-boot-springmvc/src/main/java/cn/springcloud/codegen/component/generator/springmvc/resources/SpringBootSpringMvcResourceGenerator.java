package cn.springcloud.codegen.component.generator.springmvc.resources;

import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

import java.util.HashMap;
import java.util.Map;

public class SpringBootSpringMvcResourceGenerator extends SkeletonFileGenerator {

	public SpringBootSpringMvcResourceGenerator(SkeletonContext skeletonContext,
			SkeletonProperties skeletonProperties) {
		super(skeletonContext.clone("springBootSpringMvc", SpringBootSpringMvcResourceGenerator.class), skeletonProperties);
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
	 protected String getOutputPath() {
	        return super.getOutputPath() + SkeletonConstant.MAIN_RESOURCES_FILE_PATH;
	    }
	@Override
	protected Object getDataModel() {
	    Map<String,Object>  dataMap=new  HashMap<String,Object>();
	    dataMap.put("serviceName", skeletonProperties.getString("projectName")+"-"+getSkeletonContext().getProjectType());
//	    dataMap.put("port", skeletonProperties.getString("port"));
	  
		return dataMap;
	}

}
