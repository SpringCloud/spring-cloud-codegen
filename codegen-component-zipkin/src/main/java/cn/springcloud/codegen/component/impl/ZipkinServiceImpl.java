package cn.springcloud.codegen.component.impl;

import java.io.IOException;

import cn.springcloud.codegen.component.generator.common.LogbackXmlGenerator;
import cn.springcloud.codegen.component.generator.zipkin.java.ZipkinJavaClassGenerator;
import cn.springcloud.codegen.component.generator.zipkin.resources.ZipkinResourcesGenerator;
import cn.springcloud.codegen.engine.context.SkeletonContext;
import cn.springcloud.codegen.engine.exception.SkeletonException;
import cn.springcloud.codegen.engine.property.SkeletonProperties;
import cn.springcloud.codegen.framework.service.SkeletonService;
import freemarker.template.TemplateException;

public class ZipkinServiceImpl implements  SkeletonService {

	@Override
	public void generate(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties)
			throws SkeletonException, TemplateException, IOException {
	    //创建src/main/java
		new ZipkinJavaClassGenerator(skeletonContext, skeletonProperties).generate();
		//创建src/main/resource
		new ZipkinResourcesGenerator(skeletonContext, skeletonProperties).generate();
		//创建log
		new LogbackXmlGenerator(skeletonContext, skeletonProperties, "zipkin").generate();
		//创建ZipkinPom
		SkeletonService service = new CommonServiceImpl("zipkin");
		service.generate(skeletonContext, skeletonProperties);
	}

}
