package cn.springcloud.codegen.component.generator.springmvc.resources;

import java.util.HashMap;
import java.util.Map;

import cn.springcloud.codegen.engine.constant.SkeletonConstant;
import cn.springcloud.codegen.engine.context.SkeletonContext;
import cn.springcloud.codegen.engine.generator.SkeletonFileGenerator;
import cn.springcloud.codegen.engine.property.SkeletonProperties;

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
	    dataMap.put("serviceName", skeletonProperties.getString("serviceName")+"-"+getSkeletonContext().getProjectType());
	    dataMap.put("port", skeletonProperties.getString("port"));
	  
		return dataMap;
	}

}
