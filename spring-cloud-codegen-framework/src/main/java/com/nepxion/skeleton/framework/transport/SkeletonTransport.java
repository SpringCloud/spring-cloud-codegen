package com.nepxion.skeleton.framework.transport;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.entity.SkeletonGroup;
import com.nepxion.skeleton.engine.exception.SkeletonException;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.engine.transport.SkeletonConfigTransport;
import com.nepxion.skeleton.engine.transport.SkeletonDataTransport;
import com.nepxion.skeleton.framework.service.SkeletonService;

public class SkeletonTransport {
    private static final String SKELETON_PREFIX_TEMPLATE_PATH = "skeleton.prefix.template.path";
    private static final String SKELETON_REDUCED_TEMPLATE_PATH = "skeleton.reduced.template.path";
    private static final String SKELETON_DYNAMIC_TEMPLATE_PATH_KEY = "skeleton.dynamic.template.path.key";
    private static final String SKELETON_GENERATE_FILE_NAME = "skeleton.generate.file.name";
    private static final String SKELETON_GENERATE_PATH = "skeleton.generate.path";

    private String skeletonPrefixTemplatePath;
    private String skeletonReducedTemplatePath;
    private String skeletonDynamicTemplatePathKey;
    private String skeletonGenerateFileName;
    private String skeletonGeneratePath;

    private SkeletonConfigTransport configTransport;
    private SkeletonDataTransport dataTransport;

    public SkeletonTransport(String skeletonPlugin, SkeletonService skeletonService) {
        this.configTransport = new SkeletonConfigTransport(skeletonPlugin);
        this.dataTransport = new SkeletonDataTransport() {
            @Override
            public void generate(String generatePath, SkeletonProperties skeletonProperties) throws Exception {
                String dynamicTemplatePath = generateDynamicTemplatePath(skeletonProperties);

                skeletonService.generate(new SkeletonContext(generatePath, dynamicTemplatePath, skeletonReducedTemplatePath), skeletonProperties);
            }
        };
        this.skeletonPrefixTemplatePath = configTransport.getContextProperties().getString(SKELETON_PREFIX_TEMPLATE_PATH);
        this.skeletonReducedTemplatePath = configTransport.getContextProperties().getString(SKELETON_REDUCED_TEMPLATE_PATH);
        this.skeletonDynamicTemplatePathKey = configTransport.getContextProperties().getString(SKELETON_DYNAMIC_TEMPLATE_PATH_KEY);
        this.skeletonGenerateFileName = configTransport.getContextProperties().getString(SKELETON_GENERATE_FILE_NAME);
        this.skeletonGeneratePath = configTransport.getContextProperties().getString(SKELETON_GENERATE_PATH);
    }

    public List<SkeletonGroup> getMetaData() {
        return configTransport.getMetaData();
    }

    public byte[] downloadBytes(String config) {
        SkeletonProperties properties = configTransport.getProperties(config);

        return dataTransport.download(skeletonGeneratePath, skeletonGenerateFileName, properties);
    }

    public ResponseEntity<Resource> downloadResponse(String config) {
        SkeletonProperties properties = configTransport.getProperties(config);

        String canonicalFileName = configTransport.getCanonicalFileName(skeletonGenerateFileName, properties);
        byte[] bytes = dataTransport.download(skeletonGeneratePath, skeletonGenerateFileName, properties);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("charset", "utf-8");

        headers.add("Content-Disposition", "attachment;filename=\"" + canonicalFileName + "\"");

        InputStream inputStream = new ByteArrayInputStream(bytes);
        Resource resource = new InputStreamResource(inputStream);

        return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/x-msdownload")).body(resource);
    }

    private String generateDynamicTemplatePath(SkeletonProperties properties) {
        if (StringUtils.isEmpty(skeletonDynamicTemplatePathKey)) {
            return skeletonPrefixTemplatePath;
        }

        String skeletonDynamicTemplatePathValue = properties.getString(skeletonDynamicTemplatePathKey);
        if (StringUtils.isEmpty(skeletonDynamicTemplatePathValue)) {
            throw new SkeletonException(skeletonDynamicTemplatePathKey + " is null or empty");
        }

        return skeletonPrefixTemplatePath + "/" + skeletonDynamicTemplatePathValue;
    }
}