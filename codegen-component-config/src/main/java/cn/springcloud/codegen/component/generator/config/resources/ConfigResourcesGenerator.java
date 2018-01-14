package cn.springcloud.codegen.component.generator.config.resources;



import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zzf
 * @date: 2017/12/22
 * @time: 23:59
 * @description : this is configuretion resources generator
 */
public class ConfigResourcesGenerator extends SkeletonFileGenerator {

    public ConfigResourcesGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) {
        super(skeletonContext.clone("config", ConfigResourcesGenerator.class), skeletonProperties);
    }

    @Override
    protected String getFileName() {
        return "application.properties";
    }

    @Override
    protected String getTemplateName() {
        return "application.properties.template";
    }

    @Override
    protected String getOutputPath() {
        return super.getOutputPath() + SkeletonConstant.MAIN_RESOURCES_FILE_PATH;
    }

    @Override
    protected Object getDataModel() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        // dataModel.put("gitUrl", skeletonProperties.getString("gitUrl"));
        /**
         * git 用户名和密码应该不需要， 只要给生成properties 节点注释掉就可以
         */
        dataModel.put("serviceName", skeletonProperties.getString("projectName") + "-" + getSkeletonContext().getProjectType());
        return dataModel;
    }
}
