package cn.springcloud.codegen.service;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.io.IOException;

import javax.naming.spi.StateFactory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.springcloud.codegen.component.adapter.context.StrategyContext;
import cn.springcloud.codegen.component.adapter.factory.StrategyFactory;
import cn.springcloud.codegen.component.impl.ConfigServiceImpl;
import cn.springcloud.codegen.component.impl.EurekaServiceImpl;
import cn.springcloud.codegen.component.impl.SpringBootSpringMvcServiceImpl;
import cn.springcloud.codegen.component.impl.ZipkinServiceImpl;
import cn.springcloud.codegen.component.impl.ZuulServiceImpl;
import cn.springcloud.codegen.engine.context.SkeletonContext;
import cn.springcloud.codegen.engine.exception.SkeletonException;
import cn.springcloud.codegen.engine.property.SkeletonProperties;
import cn.springcloud.codegen.framework.annotation.SkeletonPlugin;
import cn.springcloud.codegen.framework.service.SkeletonService;
import freemarker.template.TemplateException;

@Component
@SkeletonPlugin
public class SmartCodeGenServiceImpl implements SkeletonService {
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
		String scAlone = skeletonProperties.getString("scAloneType");
		if (StringUtils.equals(scAlone, "eurekaServer")) {
			eurekaService.generate(skeletonContext, skeletonProperties);
		} else if (StringUtils.equals(scAlone, "zuulServer")) {
			zuulService.generate(skeletonContext, skeletonProperties);
		} else if (StringUtils.equals(scAlone, "configServer")) {
			configService.generate(skeletonContext, skeletonProperties);
		} else if (StringUtils.equalsIgnoreCase(scAlone, "zipkinServer")) {
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