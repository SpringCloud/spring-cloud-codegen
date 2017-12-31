package com.nepxion.skeleton.engine.property;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.nepxion.skeleton.engine.util.IOUtil;

public class SkeletonContent {
    private String content;

    public SkeletonContent(String path, String encoding) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = IOUtil.getInputStream(path);
            this.content = IOUtils.toString(inputStream, encoding);
        } finally {
            if (inputStream != null) {
                IOUtils.closeQuietly(inputStream);
            }
        }
    }

    public SkeletonContent(File file, String encoding) throws IOException {
        this.content = FileUtils.readFileToString(file, encoding);
    }

    public SkeletonContent(StringBuilder stringBuilder) throws IOException {
        this.content = stringBuilder.toString();
    }

    public String getContent() {
        return content;
    }
}