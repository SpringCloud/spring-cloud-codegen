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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nepxion.skeleton.engine.entity.SkeletonGroup;
import com.nepxion.skeleton.engine.exception.SkeletonException;
import com.nepxion.skeleton.framework.aop.SkeletonBeanPostProcessor;
import com.nepxion.skeleton.framework.service.SkeletonService;
import com.nepxion.skeleton.framework.transport.SkeletonTransport;

@RestController
@Api(tags = { "脚手架接口" })
public class SkeletonController {
    @Value("${skeleton.default.plugin:}")
    private String skeletonDefaultPlugin;

    @Autowired
    private SkeletonBeanPostProcessor skeletonBeanPostProcessor;

    @Autowired
    private Map<String, SkeletonService> skeletonServiceMap;

    private Map<String, SkeletonTransport> skeletonTransportMap;

    private List<String> skeletonPlugins;

    @PostConstruct
    private void initialize() {
        if (MapUtils.isEmpty(skeletonServiceMap)) {
            throw new SkeletonException("Skeleton service map isn't injected or empty");
        }

        Map<Object, String> skeletonPluginMap = skeletonBeanPostProcessor.getSkeletonPluginMap();
        skeletonTransportMap = new HashMap<String, SkeletonTransport>(skeletonServiceMap.size());
        skeletonPlugins = new ArrayList<String>();
        for (Map.Entry<String, SkeletonService> entry : skeletonServiceMap.entrySet()) {
            SkeletonService skeletonService = entry.getValue();
            String skeletonPlugin = skeletonPluginMap.get(skeletonService);
            SkeletonTransport skeletonTransport = new SkeletonTransport(skeletonPlugin, skeletonService);
            skeletonTransportMap.put(skeletonPlugin, skeletonTransport);
            skeletonPlugins.add(skeletonPlugin);
        }
    }

    @RequestMapping(value = "/getPlugins", method = RequestMethod.GET)
    @ApiOperation(value = "获取脚手架插件名列表接口", notes = "获取脚手架插件列表接口", response = List.class, httpMethod = "GET")
    public List<String> getSkeletonPlugins() {
        return skeletonPlugins;
    }

    @RequestMapping(value = "/getMetaData", method = RequestMethod.GET)
    @ApiOperation(value = "获取默认元数据接口", notes = "获取默认界面驱动的元数据接口", response = List.class, httpMethod = "GET")
    public List<SkeletonGroup> getMetaData() {
        return getSkeletonTransport(skeletonDefaultPlugin).getMetaData();
    }

    @RequestMapping(value = "/getMetaData/{skeletonPlugin}", method = RequestMethod.GET)
    @ApiOperation(value = "获取元数据接口", notes = "根据脚手架插件名，获取对应的界面驱动的元数据接口", response = List.class, httpMethod = "GET")
    public List<SkeletonGroup> getMetaData(@PathVariable(value = "skeletonPlugin") @ApiParam(value = "脚手架插件名", required = true) String skeletonPlugin) {
        return getSkeletonTransport(skeletonPlugin).getMetaData();
    }

    @RequestMapping(value = "/downloadBytes", method = RequestMethod.POST)
    @ApiOperation(value = "下载默认脚手架", notes = "下载默认脚手架Zip文件的接口，返回Zip文件的byte数组类型", response = byte[].class, httpMethod = "POST")
    public byte[] downloadBytes(@RequestBody @ApiParam(value = "配置文件内容，可拷贝src/main/resources/config/skeleton-data.properties的内容", required = true) String config) {
        return getSkeletonTransport(skeletonDefaultPlugin).downloadBytes(config);
    }

    @RequestMapping(value = "/downloadBytes/{skeletonPlugin}", method = RequestMethod.POST)
    @ApiOperation(value = "下载脚手架", notes = "根据脚手架插件名，下载脚手架Zip文件的接口，返回Zip文件的byte数组类型", response = byte[].class, httpMethod = "POST")
    public byte[] downloadBytes(@PathVariable(value = "skeletonPlugin") @ApiParam(value = "脚手架插件名", required = true) String skeletonPlugin, @RequestBody @ApiParam(value = "配置文件内容，可拷贝src/main/resources/config/skeleton-data.properties的内容", required = true) String config) {
        return getSkeletonTransport(skeletonPlugin).downloadBytes(config);
    }

    @RequestMapping(value = "/downloadResponse", method = RequestMethod.POST)
    @ApiOperation(value = "下载默认脚手架", notes = "下载默认脚手架Zip文件的接口，返回Zip文件的ResponseEntity类型", response = ResponseEntity.class, httpMethod = "POST")
    public ResponseEntity<Resource> downloadResponse(@RequestBody @ApiParam(value = "配置文件内容，可拷贝src/main/resources/config/skeleton-data.properties的内容", required = true) String config) {
        return getSkeletonTransport(skeletonDefaultPlugin).downloadResponse(config);
    }

    @RequestMapping(value = "/downloadResponse/{skeletonPlugin}", method = RequestMethod.POST)
    @ApiOperation(value = "下载脚手架", notes = "根据脚手架插件名，下载脚手架Zip文件的接口，返回Zip文件的ResponseEntity类型", response = ResponseEntity.class, httpMethod = "POST")
    public ResponseEntity<Resource> downloadResponse(@PathVariable(value = "skeletonPlugin") @ApiParam(value = "脚手架插件名", required = true) String skeletonPlugin, @RequestBody @ApiParam(value = "配置文件内容，可拷贝src/main/resources/config/skeleton-data.properties的内容", required = true) String config) {
        return getSkeletonTransport(skeletonPlugin).downloadResponse(config);
    }

    private SkeletonTransport getSkeletonTransport(String skeletonPlugin) {
        if (MapUtils.isEmpty(skeletonTransportMap)) {
            throw new SkeletonException("Skeleton service map isn't injected or empty");
        }

        SkeletonTransport skeletonTransport = skeletonTransportMap.get(skeletonPlugin);
        if (skeletonTransport == null) {
            if (StringUtils.isEmpty(skeletonPlugin)) {
                throw new SkeletonException("No configuration existed for default skeleton plugin");
            } else {
                throw new SkeletonException("No configuration existed for skeleton plugin=" + skeletonPlugin);
            }
        }

        return skeletonTransport;
    }
}