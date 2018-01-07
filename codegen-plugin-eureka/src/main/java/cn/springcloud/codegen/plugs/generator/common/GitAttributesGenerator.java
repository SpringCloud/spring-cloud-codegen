package cn.springcloud.codegen.plugs.generator.common;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import cn.springcloud.codegen.engine.context.SkeletonContext;
import cn.springcloud.codegen.engine.generator.SkeletonFileGenerator;
import cn.springcloud.codegen.engine.property.SkeletonProperties;

public class GitAttributesGenerator extends SkeletonFileGenerator {
    public GitAttributesGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) {
        super(skeletonContext.clone(null, GitAttributesGenerator.class), skeletonProperties);
    }

    @Override
    protected String getFileName() {
        return ".gitattributes";
    }

    @Override
    protected String getTemplateName() {
        return "file.gitattributes.template";
    }

    @Override
    protected Object getDataModel() {
        return null;
    }
}