package cn.springcloud.codegen.strategy.pattern;

import java.util.HashMap;
import java.util.Map;

import cn.springcloud.codegen.strategy.entity.ApplicationType;

/**
 * 策略工厂
 * @author xujin
 * @site www.xujin.org
 */
public class StrategyFactory {
    private static StrategyFactory factory = new StrategyFactory();
    private static Map<String, Strategy> strategyMap = new HashMap<>();

    static {
        strategyMap.put(ApplicationType.SPRING_BOOT.key(), new GeneratorSpringBootStrategy());
    }

    private StrategyFactory() {

    }

    public Strategy creator(String key) {
        return strategyMap.get(key);
    }

    public static StrategyFactory getInstance() {
        return factory;
    }
}