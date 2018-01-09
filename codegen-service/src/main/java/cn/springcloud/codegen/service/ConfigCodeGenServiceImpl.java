package cn.springcloud.codegen.service;

import cn.springcloud.codegen.component.impl.ConfigServiceImpl;
import cn.springcloud.codegen.engine.context.SkeletonContext;
import cn.springcloud.codegen.engine.exception.SkeletonException;
import cn.springcloud.codegen.engine.property.SkeletonProperties;
import cn.springcloud.codegen.framework.annotation.SkeletonPlugin;
import cn.springcloud.codegen.framework.service.SkeletonService;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zzf
 * @date: 2018/1/9
 * @time: 14:48
 * @description : do some thing
 */
@Component
@SkeletonPlugin(name = "config-server")
public class ConfigCodeGenServiceImpl implements SkeletonService {
    @Override
    public void generate(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        new ConfigServiceImpl().generate(skeletonContext, skeletonProperties);
    }
}