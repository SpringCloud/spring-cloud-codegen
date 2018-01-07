package cn.springcloud.codegen;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ cn.springcloud.codegen.framework.config.SkeletonWebConfig.class })
@SpringBootApplication
public class SmartCodeGenApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SmartCodeGenApplication.class).web(true).run(args);
    }
}