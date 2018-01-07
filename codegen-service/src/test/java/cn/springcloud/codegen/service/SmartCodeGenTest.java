package cn.springcloud.codegen.service;

import java.io.IOException;

import cn.springcloud.codegen.engine.constant.SkeletonConstant;
import cn.springcloud.codegen.engine.context.SkeletonContext;
import cn.springcloud.codegen.engine.property.SkeletonProperties;
import cn.springcloud.codegen.engine.util.SkeletonUtil;
import cn.springcloud.codegen.framework.service.SkeletonService;
import cn.springcloud.codegen.service.SmartCodeGenServiceImpl;
import freemarker.template.TemplateException;

/**
 * Created with IntelliJ IDEA.
 * @author: zzf
 * @date: 2018/1/6
 * @time: 15:35
 * @description : 代码生成器本地测试
 */
public class SmartCodeGenTest {

    public static void main(String[] args) throws IOException, TemplateException {

        // ********** 构建全局上下文对象 **********
        // 创建文件的输出的路径
        // 放在操作系统的临时目录下
        String generatePath = SkeletonUtil.getTempGeneratePath();
        //String generatePath = "D:\\spring-cloud-codegene-test";

        // 如何理解prefixTemplatePath和reducedTemplatePath含义？
        // FreeMarker规定，模板文件必须放在classpath下，即以com/...开头的路径为其classpath
        // 1. 有需求要求，模板文件移动到resources中，并希望放在template/com/a/b/c...，那么template就是prefixTemplatePath，模板的前置路径
        // 2. 有需求要求，模板文件路径如果template/com/a/b/c/...，觉得路径太长，那么a/b/c/就是reducedTemplatePath，模板路径缩减，把这部分裁剪掉
        // 3. 如果把模板文件和Generator类放在一起，则prefixTemplatePath和reducedTemplatePath同时为null即可

        // 模板文件所在的前置路径
        String prefixTemplatePath = "templates";
        // String prefixTemplatePath = null;

        // 模板路径缩减
        String reducedTemplatePath = "cn/springcloud/codegen/component/";
        // String reducedTemplatePath = null;

        // 全局上下文对象
        SkeletonContext skeletonContext = new SkeletonContext(generatePath, prefixTemplatePath, reducedTemplatePath);
        // **************************************

        // ********** 构建全局配置类对象 **********
        // 描述规则的配置文件所在的路径
        // 配置文件含中文，stringEncoding必须为GBK，readerEncoding必须为UTF-8，文本文件编码必须为ANSI
        String propertiesPath = "config/skeleton-data.properties";

        // 全局配置类对象
        SkeletonProperties skeletonProperties = new SkeletonProperties(propertiesPath, SkeletonConstant.ENCODING_GBK, SkeletonConstant.ENCODING_UTF_8);
        // **************************************

        // 输出脚手架文件
        SkeletonService skeletonService = new SmartCodeGenServiceImpl();
        skeletonService.generate(skeletonContext, skeletonProperties);
    }
}