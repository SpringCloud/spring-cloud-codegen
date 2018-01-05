package com.nepxion.skeleton.engine.generator;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.commons.io.IOUtils;

import com.nepxion.skeleton.engine.config.SkeletonConfig;
import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.entity.SkeletonFileType;
import com.nepxion.skeleton.engine.exception.SkeletonException;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class AbstractSkeletonGenerator {
    protected SkeletonContext skeletonContext;
    protected SkeletonProperties skeletonProperties;

    public AbstractSkeletonGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) {
        this.skeletonContext = skeletonContext;
        this.skeletonProperties = skeletonProperties;
    }

    public AbstractSkeletonGenerator(String generatePath, String projectType, String prefixTemplatePath, String reducedTemplatePath, Class<?> generatorClass, SkeletonProperties skeletonProperties) {
        this(new SkeletonContext(generatePath, projectType, prefixTemplatePath, reducedTemplatePath, generatorClass), skeletonProperties);
    }

    public AbstractSkeletonGenerator(String generatePath, String projectType, String baseTemplatePath, SkeletonFileType fileType, SkeletonProperties skeletonProperties) {
        this(new SkeletonContext(generatePath, projectType, baseTemplatePath, fileType), skeletonProperties);
    }

    public SkeletonContext getSkeletonContext() {
        return skeletonContext;
    }

    public SkeletonProperties getSkeletonProperties() {
        return skeletonProperties;
    }

    public Template getTemplate() throws IOException {
        SkeletonConfig skeletonConfig = skeletonContext.getConfig();
        String templateName = getTemplateName();

        return skeletonConfig.getTemplate(templateName);
    }

    public void generate() throws SkeletonException, TemplateException, IOException {
        String path = getPath();

        File file = new File(path);
        generate(file);
    }

    public void generate(File file) throws SkeletonException, TemplateException, IOException {
        Template template = getTemplate();
        Object dataModel = null;
        try {
            dataModel = getDataModel();
        } catch (Exception e) {
            throw new SkeletonException(e.getMessage(), e);
        }

        generate(file, template, dataModel);
    }

    public void generate(File file, Template template, Object dataModel) throws TemplateException, IOException {
        String filePath = file.getCanonicalPath();
        filePath = filePath.replace("\\", SkeletonConstant.FILE_SEPARATOR);
        String directoryPath = filePath.substring(0, filePath.lastIndexOf(SkeletonConstant.FILE_SEPARATOR));

        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            directory.mkdirs();
        }

        FileOutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        try {
            outputStream = new FileOutputStream(file);

            outputStreamWriter = new OutputStreamWriter(outputStream, SkeletonConstant.ENCODING_UTF_8);
            template.process(dataModel, outputStreamWriter);
        } catch (TemplateException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            if (outputStreamWriter != null) {
                IOUtils.closeQuietly(outputStreamWriter);
            }

            if (outputStream != null) {
                outputStream.flush();
                IOUtils.closeQuietly(outputStream);
            }
        }
    }

    protected abstract String getTemplateName();

    protected abstract String getPath() throws SkeletonException;

    protected abstract Object getDataModel();
}