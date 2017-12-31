package com.nepxion.skeleton.engine.util;

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
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.nepxion.skeleton.engine.constant.SkeletonConstant;

public class StringUtil {
    public static String firstLetterToUpper(String value) {
        Character character = Character.toUpperCase(value.charAt(0));

        return character.toString().concat(value.substring(1));
    }

    public static String firstLetterToLower(String value) {
        Character character = Character.toLowerCase(value.charAt(0));

        return character.toString().concat(value.substring(1));
    }

    public static List<String> readLines(String value) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = IOUtils.toInputStream(value, SkeletonConstant.ENCODING_UTF_8);
            return IOUtils.readLines(inputStream, SkeletonConstant.ENCODING_UTF_8);
        } finally {
            if (inputStream != null) {
                IOUtils.closeQuietly(inputStream);
            }
        }
    }
}