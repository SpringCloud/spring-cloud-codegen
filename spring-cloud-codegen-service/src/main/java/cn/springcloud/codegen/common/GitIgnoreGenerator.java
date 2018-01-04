package cn.springcloud.codegen.common;

import cn.springcloud.codegen.engine.generator.SkeletonFileGenerator;
import cn.springcloud.codegen.engine.property.SkeletonProperties;

/**
 * @author xujin
 */
public class GitIgnoreGenerator extends SkeletonFileGenerator {

    public GitIgnoreGenerator(String generatePath, String projectType, String prefixTemplatePath, String reducedTemplatePath, SkeletonProperties skeletonProperties) {
        super(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, GitIgnoreGenerator.class, skeletonProperties);
    }

    @Override
    protected String getFileName() {
        return ".gitignore";
    }

    @Override
    protected String getTemplateName() {
        return "file.gitignore.template";
    }

    @Override
    protected Object getDataModel() {
        return null;
    }
}