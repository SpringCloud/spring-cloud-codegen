package cn.springcloud.codegen.component.impl;

import cn.springcloud.codegen.component.generator.common.GitIgnoreGenerator;
import cn.springcloud.codegen.component.generator.common.GitAttributesGenerator;
import cn.springcloud.codegen.component.generator.common.LogbackXmlGenerator;
import cn.springcloud.codegen.component.generator.zuul.ZuulPomXmlGenerator;
import cn.springcloud.codegen.component.generator.zuul.java.ZuulJavaClassGenerator;
import cn.springcloud.codegen.component.generator.zuul.resources.ZuulResourcesGenerator;
import cn.springcloud.codegen.engine.context.SkeletonContext;
import cn.springcloud.codegen.engine.exception.SkeletonException;
import cn.springcloud.codegen.engine.property.SkeletonProperties;
import cn.springcloud.codegen.framework.service.SkeletonService;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @author Vincent.
 * @createdOn 2018/01/08 22:24
 */
public class ZuulServiceImpl implements SkeletonService {
    @Override
    public void generate(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {

        // 创建Java类文件到main/java 目录下
        new ZuulJavaClassGenerator(skeletonContext, skeletonProperties).generate();

        // 创建文件到main/resources目录下面
        new ZuulResourcesGenerator(skeletonContext, skeletonProperties).generate();
        new LogbackXmlGenerator(skeletonContext, skeletonProperties, "zuul").generate();

        // 生产pom 文件到跟目录下
        new ZuulPomXmlGenerator(skeletonContext, skeletonProperties).generate();
        new GitIgnoreGenerator(skeletonContext, skeletonProperties, "zuul").generate();
        new GitAttributesGenerator(skeletonContext, skeletonProperties, "zuul").generate();
    }
}
