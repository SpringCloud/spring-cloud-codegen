package cn.springcloud.codegen.service;

import cn.springcloud.codegen.component.impl.ZuulServiceImpl;
import cn.springcloud.codegen.engine.context.SkeletonContext;
import cn.springcloud.codegen.engine.exception.SkeletonException;
import cn.springcloud.codegen.engine.property.SkeletonProperties;
import cn.springcloud.codegen.framework.annotation.SkeletonPlugin;
import cn.springcloud.codegen.framework.service.SkeletonService;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Vincent.
 * @createdOn 2018/01/08 23:16
 */
@Component
@SkeletonPlugin(name = "zuul-server-codegen")
public class ZuulCodeGenServiceImpl implements SkeletonService {
    @Override
    public void generate(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        new ZuulServiceImpl().generate(skeletonContext, skeletonProperties);
    }
}