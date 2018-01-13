package cn.springcloud.codegen.component.generator.springmvc.java;

import java.util.HashMap;
import java.util.Map;

import cn.springcloud.codegen.engine.constant.SkeletonConstant;
import cn.springcloud.codegen.engine.context.SkeletonContext;
import cn.springcloud.codegen.engine.generator.SkeletonJavaGenerator;
import cn.springcloud.codegen.engine.property.SkeletonProperties;

public class SpringBootSpringMvcJavaClassGenertor extends SkeletonJavaGenerator {

	public SpringBootSpringMvcJavaClassGenertor(SkeletonContext skeletonContext,
			SkeletonProperties skeletonProperties) {
		super(skeletonContext.clone("springBootSpringMvc", SpringBootSpringMvcJavaClassGenertor.class), skeletonProperties);
		
	}

	@Override
	protected String getClassName() {
		
		return "SpringBootSpringMvcApplication";
	}

	@Override
	protected boolean isMainCode() {
		
		return true;
	}

	@Override
	protected String getTemplateName() {
		
		return "SpringBootSpringMvcApplication.java.template";
	}

	@Override
	protected Object getDataModel() {
		Map<String,Object> dataMap=new HashMap<String,Object>();
		dataMap.put(SkeletonConstant.PACKAGE, getPackage());
		return dataMap;
	}

}
