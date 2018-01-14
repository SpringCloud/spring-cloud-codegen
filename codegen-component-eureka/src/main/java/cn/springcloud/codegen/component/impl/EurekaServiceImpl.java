package cn.springcloud.codegen.component.impl;

import java.io.IOException;


import cn.springcloud.codegen.component.generator.eureka.EurekaPomXmlGenerator;
import cn.springcloud.codegen.component.generator.eureka.java.EurekaJavaClassGenerator;
import cn.springcloud.codegen.component.generator.eureka.resources.EurekaResourcesGenerator;

import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.exception.SkeletonException;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.framework.service.SkeletonService;
import freemarker.template.TemplateException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zzf
 * @date: 2018/1/5
 * @time: 10:48
 * @description : do some thing
 */
public class EurekaServiceImpl implements SkeletonService {
    @Override
    public void generate(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {

        // 创建Java类文件到main/java 目录下
        new EurekaJavaClassGenerator(skeletonContext, skeletonProperties).generate();

        // 创建文件到main/resources目录下面
        new EurekaResourcesGenerator(skeletonContext, skeletonProperties).generate();
//        new LogbackXmlGenerator(skeletonContext, skeletonProperties, "eureka").generate();

        // 生产pom 文件到跟目录下
        new EurekaPomXmlGenerator(skeletonContext, skeletonProperties).generate();

        //生成公共配置文件到根目录下
        SkeletonService service = new CommonServiceImpl("eureka");
        service.generate(skeletonContext, skeletonProperties);
    }
}
