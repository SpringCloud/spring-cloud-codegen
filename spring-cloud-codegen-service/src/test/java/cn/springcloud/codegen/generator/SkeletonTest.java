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

import cn.springcloud.codegen.service.SkeletonServiceImpl;

import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.engine.util.SkeletonUtil;
import com.nepxion.skeleton.framework.service.SkeletonService;

public class SkeletonTest {
    public static void main(String[] args) throws Exception {
        // ********** 构建全局上下文对象 **********
        // 创建文件的输出的路径
        // 放在操作系统的临时目录下
        String generatePath = SkeletonUtil.getTempGeneratePath();
        // String generatePath = "E:/Download/skeleton/";

        // 如何理解prefixTemplatePath和reducedTemplatePath含义？
        // FreeMarker规定，模板文件必须放在classpath下，即以com/...开头的路径为其classpath
        // 1. 有需求要求，模板文件移动到resources中，并希望放在template/com/a/b/c...，那么template就是prefixTemplatePath，模板的前置路径
        // 2. 有需求要求，模板文件路径如果template/com/a/b/c/...，觉得路径太长，那么a/b/c/就是reducedTemplatePath，模板路径缩减，把这部分裁剪掉       
        // 3. 如果把模板文件和Generator类放在一起，则prefixTemplatePath和reducedTemplatePath同时为null即可

        // 模板文件所在的前置路径
        String prefixTemplatePath = "templates/springboot"; 
        // String prefixTemplatePath = null;

        // 模板路径缩减
        String reducedTemplatePath = "cn/springcloud/codegen/generator/";
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
        SkeletonService skeletonService = new SkeletonServiceImpl();
        skeletonService.generate(skeletonContext, skeletonProperties);
    }
}