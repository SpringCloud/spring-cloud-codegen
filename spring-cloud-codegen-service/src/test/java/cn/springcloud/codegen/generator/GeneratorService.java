package cn.springcloud.codegen.generator;

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

import cn.springcloud.codegen.generator.server.PomXmlGenerator;
import cn.springcloud.codegen.generator.server.java.ServerApplicationClassGenerator;
import cn.springcloud.codegen.generator.server.java.TestServerApplicationClassGenerator;
import cn.springcloud.codegen.generator.server.resources.ApplicationPropertiesGenerator;
import cn.springcloud.codegen.generator.server.resources.LogbackXmlGenerator;

import com.nepxion.skeleton.exception.SkeletonException;
import com.nepxion.skeleton.property.SkeletonProperties;

import freemarker.template.TemplateException;

public class GeneratorService {
    public void generator(String path, String prefixTemplateDirectory, String reducedTemplateDirectory, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        // 创建Java类文件到main/java目录下
        new ServerApplicationClassGenerator(path, "server", prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();

        // 创建Java类文件到test/java目录下
        new TestServerApplicationClassGenerator(path, "server", prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();

        // 创建文件到main/resources目录下
        new ApplicationPropertiesGenerator(path, "server", prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();

        // 创建文件到test/resources目录下
        new LogbackXmlGenerator(path, "server", prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();

        // 创建文件到根目录下
        new PomXmlGenerator(path, "server", prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties).generate();
    }
}