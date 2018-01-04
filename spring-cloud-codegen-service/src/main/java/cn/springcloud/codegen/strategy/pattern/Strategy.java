package cn.springcloud.codegen.strategy.pattern;

import java.io.File;

import cn.springcloud.codegen.strategy.entity.ApplicationType;

/**
 * 使用策略模式决定生成代码
 * @author xujin
 * @site www.xujin.org
 */
public interface Strategy {
    /**
     * @param key
     * @param appTypeEnum
     * @return
     */
    File generateByKey(String key, ApplicationType appTypeEnum);
}