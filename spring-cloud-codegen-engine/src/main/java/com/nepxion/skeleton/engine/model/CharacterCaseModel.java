package com.nepxion.skeleton.engine.model;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class CharacterCaseModel implements TemplateDirectiveModel {
    private boolean upperCase = true;

    public CharacterCaseModel(boolean upperCase) {
        this.upperCase = upperCase;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        if (!params.isEmpty()) {
            throw new TemplateModelException("Params should be empty");
        }

        if (loopVars.length != 0) {
            throw new TemplateModelException("LoopVars should be empty");
        }

        if (body == null) {
            throw new TemplateModelException("Body can't be null");
        }

        body.render(new CharacterCaseWriter(env.getOut(), upperCase));
    }
}