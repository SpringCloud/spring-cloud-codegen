package cn.springcloud.codegen.plugs.impl;

import cn.springcloud.codegen.engine.context.SkeletonContext;
import cn.springcloud.codegen.engine.exception.SkeletonException;
import cn.springcloud.codegen.engine.property.SkeletonProperties;
import cn.springcloud.codegen.framework.service.SkeletonService;

import cn.springcloud.codegen.plugs.generator.common.LogbackXmlGenerator;
import cn.springcloud.codegen.plugs.generator.eureka.EurekaPomXmlGenerator;
import cn.springcloud.codegen.plugs.generator.eureka.java.EurekaJavaClassGenerator;
import cn.springcloud.codegen.plugs.generator.eureka.resources.EurekaResourcesGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zzf
 * @date: 2018/1/5
 * @time: 10:48
 * @description : do some thing
 */
public class EurekaProjectServiceImpl implements SkeletonService {
    @Override
    public void generate(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {

        // 创建Java类文件到main/java 目录下
        new EurekaJavaClassGenerator(skeletonContext, skeletonProperties);

        // 创建文件到main/resources目录下面
        new EurekaResourcesGenerator(skeletonContext, skeletonProperties);
        new LogbackXmlGenerator(skeletonContext, skeletonProperties, "eureka").generate();

        // 生产pom 文件到跟目录下
        new EurekaPomXmlGenerator(skeletonContext, skeletonProperties).generate();

    }
}
