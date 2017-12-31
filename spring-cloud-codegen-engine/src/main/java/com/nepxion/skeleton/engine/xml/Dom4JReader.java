package com.nepxion.skeleton.engine.xml;

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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

public class Dom4JReader {
    public static Document getDocument(String text) throws DocumentException {
        return DocumentHelper.parseText(text);
    }

    public static Document getDocument(File file, String encoding) throws DocumentException, IOException, UnsupportedEncodingException {
        InputStream inputStream = new FileInputStream(file);

        return getDocument(inputStream, encoding);
    }

    public static Document getDocument(InputStream inputStream, String encoding) throws DocumentException, IOException {
        SAXReader saxReader = new SAXReader();
        if (StringUtils.isNotEmpty(encoding)) {
            saxReader.setEncoding(encoding);
        }

        Document document = null;
        try {
            document = saxReader.read(inputStream);
        } catch (DocumentException e) {
            throw e;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return document;
    }

    public static Document getDocument(InputSource inputSource, String encoding) throws DocumentException {
        inputSource.setEncoding(encoding);

        SAXReader saxReader = new SAXReader();
        if (StringUtils.isNotEmpty(encoding)) {
            saxReader.setEncoding(encoding);
        }

        return saxReader.read(inputSource);
    }

    public static Document getDocument(Reader reader, String encoding) throws DocumentException, IOException {
        SAXReader saxReader = new SAXReader();
        if (StringUtils.isNotEmpty(encoding)) {
            saxReader.setEncoding(encoding);
        }

        Document document = null;
        try {
            document = saxReader.read(reader);
        } catch (DocumentException e) {
            throw e;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return document;
    }

    public static Document getDocument(URL url, String encoding) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        if (StringUtils.isNotEmpty(encoding)) {
            saxReader.setEncoding(encoding);
        }

        return saxReader.read(url);
    }
}