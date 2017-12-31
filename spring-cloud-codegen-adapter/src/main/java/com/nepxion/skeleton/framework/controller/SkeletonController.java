package com.nepxion.skeleton.framework.controller;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nepxion.skeleton.engine.entity.SkeletonGroup;
import com.nepxion.skeleton.engine.exception.SkeletonException;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.engine.transport.SkeletonConfigTransport;
import com.nepxion.skeleton.engine.transport.SkeletonDataTransport;
import com.nepxion.skeleton.framework.service.SkeletonService;

@RestController
@Api(tags = { "脚手架接口" })
public class SkeletonController {
    @Value("${skeleton.prefix.template.path}")
    private String skeletonPrefixTemplatePath;

    @Value("${skeleton.reduced.template.path}")
    private String skeletonReducedTemplatePath;

    @Value("${skeleton.dynamic.template.path.key}")
    private String skeletonDynamicTemplatePathKey;

    @Value("${skeleton.generate.file.name}")
    private String skeletonGenerateFileName;

    @Value("${skeleton.generate.path}")
    private String skeletonGeneratePath;

    @Autowired
    private SkeletonService service;

    private SkeletonConfigTransport configTransport;
    private SkeletonDataTransport dataTransport;

    @PostConstruct
    private void initialize() {
        configTransport = new SkeletonConfigTransport();
        dataTransport = new SkeletonDataTransport() {
            @Override
            public void generate(String generatePath, SkeletonProperties skeletonProperties) throws Exception {
                String dynamicTemplatePath = generateDynamicTemplatePath(skeletonProperties);

                service.generate(generatePath, dynamicTemplatePath, skeletonReducedTemplatePath, skeletonProperties);
            }
        };
    }

    @RequestMapping(value = "/getMetaData", method = RequestMethod.GET)
    @ApiOperation(value = "获取元数据接口", notes = "获取根据配置文件进行界面驱动的元数据接口", response = List.class, httpMethod = "GET")
    public List<SkeletonGroup> getMetaData() {
        return configTransport.getMetaData();
    }

    @RequestMapping(value = "/downloadBytes", method = RequestMethod.POST)
    @ApiOperation(value = "下载脚手架", notes = "下载脚手架Zip文件的接口，返回Zip文件的byte数组类型", response = byte[].class, httpMethod = "POST")
    public byte[] downloadBytes(@RequestBody @ApiParam(value = "配置文件内容，可拷贝src/main/resources/skeleton-data.properties的内容", required = true) String config) {
        SkeletonProperties properties = configTransport.getProperties(config);

        return dataTransport.download(skeletonGeneratePath, skeletonGenerateFileName, properties);
    }

    @RequestMapping(value = "/downloadResponse", method = RequestMethod.POST)
    @ApiOperation(value = "下载脚手架", notes = "下载脚手架Zip文件的接口，返回Zip文件的ResponseEntity类型", response = ResponseEntity.class, httpMethod = "POST")
    public ResponseEntity<Resource> downloadResponse(@RequestBody @ApiParam(value = "配置文件内容，可拷贝src/main/resources/skeleton-data.properties的内容", required = true) String config) {
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