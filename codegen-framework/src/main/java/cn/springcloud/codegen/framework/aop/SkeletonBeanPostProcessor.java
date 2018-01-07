package cn.springcloud.codegen.framework.aop;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.util.HashMap;
import java.util.Map;

import cn.springcloud.codegen.engine.exception.SkeletonException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import cn.springcloud.codegen.framework.annotation.SkeletonPlugin;

@Component("skeletonBeanPostProcessor")
public class SkeletonBeanPostProcessor implements BeanPostProcessor {
    private Map<Object, String> skeletonPluginMap = new HashMap<Object, String>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(SkeletonPlugin.class)) {
            SkeletonPlugin skeletonPluginAnnotation = bean.getClass().getAnnotation(SkeletonPlugin.class);
            String pluginName = skeletonPluginAnnotation.name().trim();
            if (skeletonPluginMap.containsValue(pluginName)) {
                throw new SkeletonException("More than one plugin for name=" + pluginName);
            }
            skeletonPluginMap.put(bean, pluginName);
        }

        return bean;
    }

    public Map<Object, String> getSkeletonPluginMap() {
        return skeletonPluginMap;
    }
}