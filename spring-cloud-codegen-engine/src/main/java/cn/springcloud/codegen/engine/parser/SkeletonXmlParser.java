package cn.springcloud.codegen.engine.parser;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.springcloud.codegen.engine.constant.SkeletonConstant;
import cn.springcloud.codegen.engine.entity.SkeletonGroupType;
import cn.springcloud.codegen.engine.property.SkeletonProperties;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.springcloud.codegen.engine.entity.SkeletonEntity;
import cn.springcloud.codegen.engine.entity.SkeletonEntityType;
import cn.springcloud.codegen.engine.entity.SkeletonGroup;
import cn.springcloud.codegen.engine.entity.SkeletonGroupLayoutType;
import cn.springcloud.codegen.engine.xml.Dom4JParser;

public class SkeletonXmlParser extends Dom4JParser {
    private static final Logger LOG = LoggerFactory.getLogger(SkeletonXmlParser.class);

    private List<SkeletonGroup> skeletonGroups;
    private SkeletonProperties skeletonDataProperties;

    public SkeletonXmlParser(SkeletonProperties skeletonDataProperties) {
        this.skeletonDataProperties = skeletonDataProperties;
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected void parseRoot(Element element) {
        LOG.info("Start to parse xml...");

        skeletonGroups = new ArrayList<SkeletonGroup>();

        for (Iterator elementIterator = element.elementIterator(); elementIterator.hasNext();) {
            Object childElementObject = elementIterator.next();
            if (childElementObject instanceof Element) {
                Element childElement = (Element) childElementObject;
                String name = childElement.getName();

                if (StringUtils.equals(name, SkeletonConstant.GROUP)) {
                    SkeletonGroup skeletonGroup = new SkeletonGroup();

                    parseGroup(childElement, skeletonGroup);

                    skeletonGroups.add(skeletonGroup);
                }
            }
        }

        LOG.info("Skeleton groups={}", skeletonGroups);
    }

    @SuppressWarnings("rawtypes")
    private void parseGroup(Element element, SkeletonGroup skeletonGroup) {
        List<SkeletonEntity> skeletonEntities = new ArrayList<SkeletonEntity>();
        skeletonGroup.setEntityList(skeletonEntities);
        for (Iterator elementIterator = element.elementIterator(); elementIterator.hasNext();) {
            Object childElementObject = elementIterator.next();
            if (childElementObject instanceof Element) {
                Element childElement = (Element) childElementObject;
                String name = childElement.getName();
                String text = childElement.getTextTrim();

                if (StringUtils.equals(name, SkeletonConstant.KEY)) {
                    skeletonGroup.setKey(text);
                } else if (StringUtils.equals(name, SkeletonConstant.LABEL)) {
                    skeletonGroup.setLabel(text);
                } else if (StringUtils.equals(name, SkeletonConstant.DESCRIPTION)) {
                    skeletonGroup.setDescription(text);
                } else if (StringUtils.equals(name, SkeletonConstant.TYPE)) {
                    skeletonGroup.setType(SkeletonGroupType.fromString(text));
                } else if (StringUtils.equals(name, SkeletonConstant.LAYOUT)) {
                    skeletonGroup.setLayoutType(SkeletonGroupLayoutType.fromString(text));
                } else if (StringUtils.equals(name, SkeletonConstant.TITLED_BORDER)) {
                    skeletonGroup.setTitledBorder(Boolean.parseBoolean(text));
                } else if (StringUtils.equals(name, SkeletonConstant.ENTITY)) {
                    SkeletonEntity skeletonEntity = new SkeletonEntity();

                    parseEntity(childElement, skeletonEntity);

                    skeletonEntities.add(skeletonEntity);
                }
            }
        }
    }

    @SuppressWarnings("rawtypes")
    private void parseEntity(Element element, SkeletonEntity skeletonEntity) {
        for (Iterator elementIterator = element.elementIterator(); elementIterator.hasNext();) {
            Object childElementObject = elementIterator.next();
            if (childElementObject instanceof Element) {
                Element childElement = (Element) childElementObject;
                String name = childElement.getName();
                String text = childElement.getTextTrim();

                if (StringUtils.equals(name, SkeletonConstant.KEY)) {
                    skeletonEntity.setKey(text);
                    try {
                        skeletonEntity.setValue(skeletonDataProperties.getString(text));
                    } catch (Exception e) {
                        LOG.warn("Undefined value in properties file for key={}", text);
                    }
                } else if (StringUtils.equals(name, SkeletonConstant.LABEL)) {
                    skeletonEntity.setLabel(text);
                } else if (StringUtils.equals(name, SkeletonConstant.DESCRIPTION)) {
                    skeletonEntity.setDescription(text);
                } else if (StringUtils.equals(name, SkeletonConstant.NOTE)) {
                    skeletonEntity.setNote(text);
                } else if (StringUtils.equals(name, SkeletonConstant.TYPE)) {
                    skeletonEntity.setType(SkeletonEntityType.fromString(text));
                } else if (StringUtils.equals(name, SkeletonConstant.OPTIONS)) {
                    skeletonEntity.setOptions(text.split(";"));
                } else if (StringUtils.equals(name, SkeletonConstant.HIGHLIGHTABLE)) {
                    skeletonEntity.setHighlightable(Boolean.parseBoolean(text));
                } else if (StringUtils.equals(name, SkeletonConstant.DEFAULTABLE)) {
                    skeletonEntity.setDefaultable(Boolean.parseBoolean(text));
                } else if (StringUtils.equals(name, SkeletonConstant.EMPTIABLE)) {
                    skeletonEntity.setEmptiable(Boolean.parseBoolean(text));
                } else if (StringUtils.equals(name, SkeletonConstant.EDITABLE)) {
                    skeletonEntity.setEditable(Boolean.parseBoolean(text));
                }
            }
        }
    }

    public List<SkeletonGroup> getSkeletonGroups() {
        return skeletonGroups;
    }
}