package cn.springcloud.codegen.component.impl;

import cn.springcloud.codegen.component.generator.config.ConfigPomXmlGenerator;
import cn.springcloud.codegen.component.generator.config.java.ConfigJavaClassGenerator;
import cn.springcloud.codegen.component.generator.config.resources.ConfigResourcesGenerator;

import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.exception.SkeletonException;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.framework.service.SkeletonService;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zzf
 * @date: 2018/1/8
 * @time: 22:48
 * @description : do some thing
 */
public class ConfigServiceImpl implements SkeletonService {
    @Override
    public void generate(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {

        // 创建Java类文件到main/java 目录下
        new ConfigJavaClassGenerator(skeletonContext, skeletonProperties).generate();

        // 创建文件到main/resources目录下面
        new ConfigResourcesGenerator(skeletonContext, skeletonProperties).generate();
//        new LogbackXmlGenerator(skeletonContext, skeletonProperties, "eureka").generate();

        // 生产pom 文件到跟目录下
        new ConfigPomXmlGenerator(skeletonContext, skeletonProperties).generate();

        //生成公共配置文件到根目录下
        SkeletonService service = new CommonServiceImpl("config");
        service.generate(skeletonContext, skeletonProperties);
    }
}
