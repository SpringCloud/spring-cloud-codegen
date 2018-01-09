package cn.springcloud.codegen.component.impl;

import cn.springcloud.codegen.component.generator.common.GitAttributesGenerator;
import cn.springcloud.codegen.component.generator.common.GitIgnoreGenerator;
import cn.springcloud.codegen.component.generator.common.LogbackXmlGenerator;
import cn.springcloud.codegen.engine.context.SkeletonContext;
import cn.springcloud.codegen.engine.exception.SkeletonException;
import cn.springcloud.codegen.engine.property.SkeletonProperties;
import cn.springcloud.codegen.framework.service.SkeletonService;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @author Vincent.
 * @createdOn 2018/01/09 21:24
 */
public class CommonServiceImpl implements SkeletonService{
    private String projectType;

    public CommonServiceImpl(String projectType){
        super();
        this.projectType = projectType;
    }
    @Override
    public void generate(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        new GitAttributesGenerator(skeletonContext, skeletonProperties, projectType).generate();

        new GitIgnoreGenerator(skeletonContext, skeletonProperties, projectType).generate();

        new LogbackXmlGenerator(skeletonContext, skeletonProperties, projectType).generate();
    }
}
