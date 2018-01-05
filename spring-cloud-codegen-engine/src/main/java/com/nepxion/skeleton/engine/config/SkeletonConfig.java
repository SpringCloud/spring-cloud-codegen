package com.nepxion.skeleton.engine.config;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.model.CharacterCaseModel;

import freemarker.template.Configuration;

public class SkeletonConfig extends Configuration {
    private String templatePath;

    public SkeletonConfig(String templatePath) {
        super(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        // 指定模板所在的classpath目录
        setClassForTemplateLoading(SkeletonConfig.class, templatePath);

        // 指定文件编码
        setDefaultEncoding(SkeletonConstant.ENCODING_UTF_8);

        // 添加一个“宏”共享变量，用来将属性名首字母大写
        setSharedVariable(SkeletonConstant.UPPER_CASE, new CharacterCaseModel(true));

        // 添加一个“宏”共享变量，用来将属性名首字母小写
        setSharedVariable(SkeletonConstant.LOWER_CASE, new CharacterCaseModel(false));

        this.templatePath = templatePath;
    }

    public String getTemplatePath() {
        return templatePath;
    }
}