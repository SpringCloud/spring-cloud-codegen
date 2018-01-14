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
 * @description : Spring boot 插件
 */
@Component
@SkeletonPlugin(name = "springboot")
public class SpringBootCodeGenServiceImpl implements SkeletonService {

	@Override
	public void generate(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties)
			throws SkeletonException, TemplateException, IOException {

	}
}