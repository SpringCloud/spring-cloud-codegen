package cn.springcloud.codegen.component.impl;

import java.io.IOException;

import cn.springcloud.codegen.component.generator.zipkin.ZipkinPomXmlGenerator;
import cn.springcloud.codegen.component.generator.zipkin.java.ZipkinJavaClassGenerator;
import cn.springcloud.codegen.component.generator.zipkin.resources.ZipkinResourcesGenerator;
import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.exception.SkeletonException;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.framework.service.SkeletonService;
import freemarker.template.TemplateException;

public class ZipkinServiceImpl implements  SkeletonService {

	@Override
	public void generate(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties)
			throws SkeletonException, TemplateException, IOException {
	    //创建src/main/java
		new ZipkinJavaClassGenerator(skeletonContext, skeletonProperties).generate();
		//创建src/main/resource
		new ZipkinResourcesGenerator(skeletonContext, skeletonProperties).generate();
		//创建ZipkinPom
		new ZipkinPomXmlGenerator(skeletonContext, skeletonProperties).generate();

		SkeletonService service = new CommonServiceImpl("zipkin");
		service.generate(skeletonContext, skeletonProperties);
	}

}
