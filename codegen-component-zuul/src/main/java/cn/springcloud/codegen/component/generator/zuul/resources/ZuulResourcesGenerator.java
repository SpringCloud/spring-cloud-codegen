package cn.springcloud.codegen.component.generator.zuul.resources;

import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vincent.
 * @createdOn 2018/01/08 22:50
 */
public class ZuulResourcesGenerator extends SkeletonFileGenerator {

    public ZuulResourcesGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) {
        super(skeletonContext.clone("zuul", ZuulResourcesGenerator.class), skeletonProperties);
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
        dataModel.put("serviceName", skeletonProperties.getString("projectName") + "-" + getSkeletonContext().getProjectType());
//        dataModel.put("port", skeletonProperties.getString("zuulPort"));

        return dataModel;
    }
}
