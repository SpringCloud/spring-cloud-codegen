package cn.springcloud.codegen.component.impl;

import java.io.IOException;
import cn.springcloud.codegen.component.generator.springmvc.SpringBootSpringMvcPomXmlGenerator;
import cn.springcloud.codegen.component.generator.springmvc.java.SpringBootSpringMvcJavaClassGenertor;
import cn.springcloud.codegen.component.generator.springmvc.resources.SpringBootSpringMvcResourceGenerator;
import cn.springcloud.codegen.engine.context.SkeletonContext;
import cn.springcloud.codegen.engine.exception.SkeletonException;
import cn.springcloud.codegen.engine.property.SkeletonProperties;
import cn.springcloud.codegen.framework.service.SkeletonService;
import freemarker.template.TemplateException;

public class SpringBootSpringMvcServiceImpl implements SkeletonService {

	@Override
	public void generate(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties)
			throws SkeletonException, TemplateException, IOException {
		new SpringBootSpringMvcJavaClassGenertor(skeletonContext, skeletonProperties).generate();;
		new SpringBootSpringMvcPomXmlGenerator(skeletonContext, skeletonProperties).generate();;
		new SpringBootSpringMvcResourceGenerator(skeletonContext, skeletonProperties).generate();
		CommonServiceImpl  commonService=new CommonServiceImpl("springBootSpringMvc");
		commonService.generate(skeletonContext, skeletonProperties);
	}

}
