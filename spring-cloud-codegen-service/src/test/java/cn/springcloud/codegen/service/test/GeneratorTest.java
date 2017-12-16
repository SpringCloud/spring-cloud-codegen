package cn.springcloud.codegen.service.test;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import cn.springcloud.codegen.service.generator.GeneratorService;

import com.nepxion.skeleton.property.SkeletonProperties;

public class GeneratorTest {
    public static void main(String[] args) {
        try {
            // 创建文件的输出的路径
            String generatePath = "/xujin/Skeleton/";

            // 如果prefixTemplateDirectory和reducedTemplateDirectory同时为null，那么Generator类目录和Template目录必须完全一致
            // 模板文件所在的前置目录名
            String prefixTemplateDirectory = "templates/springboot";
            // String prefixTemplateDirectory = null;

            // 模板目录缩减
            String reducedTemplateDirectory = "cn/springcloud/codegen/service/generator/";
            // String reducedTemplateDirectory = null;

            // 描述规则的配置文件所在的路径
            String propertiesPath = "config/skeleton-data.properties";

            // 构造全局配置文件对象
            SkeletonProperties skeletonProperties = new SkeletonProperties(propertiesPath);

            // 输出脚手架文件
            GeneratorService generatorService = new GeneratorService();
            generatorService.generator(generatePath, prefixTemplateDirectory, reducedTemplateDirectory, skeletonProperties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}