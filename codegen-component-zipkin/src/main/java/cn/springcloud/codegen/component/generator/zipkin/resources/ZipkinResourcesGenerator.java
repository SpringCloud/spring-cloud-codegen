package cn.springcloud.codegen.component.generator.zipkin.resources;

import java.util.HashMap;
import java.util.Map;

import cn.springcloud.codegen.engine.constant.SkeletonConstant;
import cn.springcloud.codegen.engine.context.SkeletonContext;
import cn.springcloud.codegen.engine.generator.SkeletonFileGenerator;
import cn.springcloud.codegen.engine.property.SkeletonProperties;

public class ZipkinResourcesGenerator extends SkeletonFileGenerator {

	public ZipkinResourcesGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) {
		super(skeletonContext.clone("zipkin", ZipkinResourcesGenerator.class), skeletonProperties);
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serviceName",
				skeletonProperties.getString("projectName") + "-" + getSkeletonContext().getProjectType());
//		map.put("port", skeletonProperties.getString("port"));
		return map;
	}

}
