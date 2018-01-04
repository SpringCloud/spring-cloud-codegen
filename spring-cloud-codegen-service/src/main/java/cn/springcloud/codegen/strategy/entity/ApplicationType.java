package cn.springcloud.codegen.strategy.entity;

/**
 * @author xujin
 * @site www.xujin.org
 */
public enum ApplicationType {
    SPRING_BOOT("springboot", "Spring Boot应用"),
    SPRING_CLOUD("springcloud", "Spring Cloud应用");

    /**
     * key值
     */
    private String key;

    /**
     * 类型描述
     */
    private String description;

    private ApplicationType(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String key() {
        return key;
    }

    public String description() {
        return description;
    }

    public static ApplicationType keyOf(int key) {
        for (ApplicationType type : ApplicationType.values()) {
            if (type.key().equals(key)) {
                return type;
            }
        }
        return null;
    }
}