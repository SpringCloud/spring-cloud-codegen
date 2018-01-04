package cn.springcloud.codegen.strategy.pattern;

import java.io.File;

import cn.springcloud.codegen.strategy.entity.ApplicationType;

/**
 * 代码生成器执行上下文
 * @author xujin
 * @site www.xujin.org
 */
public class CodeGenContext {
    private Strategy strategy;

    public File generateProject(String key, ApplicationType appTypeEnum) {
        strategy = StrategyFactory.getInstance().creator(key);

        return strategy.generateByKey(key, appTypeEnum);
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}