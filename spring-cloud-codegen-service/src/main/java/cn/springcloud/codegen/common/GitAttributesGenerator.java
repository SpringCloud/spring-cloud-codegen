package cn.springcloud.codegen.common;

import cn.springcloud.codegen.engine.generator.SkeletonFileGenerator;
import cn.springcloud.codegen.engine.property.SkeletonProperties;

/**
 * @author xujin
 */
public class GitAttributesGenerator extends SkeletonFileGenerator {

    public GitAttributesGenerator(String generatePath, String projectType, String prefixTemplatePath, String reducedTemplatePath, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, GitAttributesGenerator.class, skeletonProperties);
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