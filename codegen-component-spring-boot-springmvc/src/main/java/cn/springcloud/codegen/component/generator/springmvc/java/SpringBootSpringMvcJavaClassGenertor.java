package cn.springcloud.codegen.component.generator.springmvc.java;

import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.generator.SkeletonJavaGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

import java.util.HashMap;
import java.util.Map;

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
