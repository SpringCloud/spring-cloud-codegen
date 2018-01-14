package cn.springcloud.codegen.service;

import cn.springcloud.codegen.component.impl.*;
import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.exception.SkeletonException;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.framework.annotation.SkeletonPlugin;
import com.nepxion.skeleton.framework.service.SkeletonService;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zzf
 * @date: 2017/1/14
 * @time: 16:30
 * @description : springcloud 插件
 */
@Component
@SkeletonPlugin(name = "springcloud")
public class SpringCloudCodeGenServiceImpl implements SkeletonService {

	private static SkeletonService eurekaService = null;
	private static SkeletonService zuulService = null;
	private static SkeletonService configService = null;
	private static SkeletonService zipkinService = null;
	private static SkeletonService springBootService = null;
	static {
		eurekaService = new EurekaServiceImpl();
		zuulService = new ZuulServiceImpl();
		configService = new ConfigServiceImpl();
		zipkinService = new ZipkinServiceImpl();
		springBootService = new SpringBootSpringMvcServiceImpl();
	}

	@Override
	public void generate(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties)
			throws SkeletonException, TemplateException, IOException {
		String scAlone = skeletonProperties.getString("sc-alone-radio");
		if (StringUtils.equals(scAlone, "eureka-server")) {
			eurekaService.generate(skeletonContext, skeletonProperties);
		} else if (StringUtils.equals(scAlone, "zuul-server")) {
			zuulService.generate(skeletonContext, skeletonProperties);
		} else if (StringUtils.equals(scAlone, "config-server")) {
			configService.generate(skeletonContext, skeletonProperties);
		} else if (StringUtils.equalsIgnoreCase(scAlone, "zipkin-server")) {
			zipkinService.generate(skeletonContext, skeletonProperties);
		} else if (StringUtils.equalsIgnoreCase(scAlone, "springBootSpringMvcServer")) {
			springBootService.generate(skeletonContext, skeletonProperties);
		}
		/*
		 * StrategyFactory.instance().invoke(scAlone, skeletonContext,
		 * skeletonProperties);
		 */
	}
}