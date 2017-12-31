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
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.xml.sax.InputSource;

import com.nepxion.skeleton.engine.util.IOUtil;

public abstract class Dom4JParser {
    public void parsePath(String path, String encoding) throws IOException, DocumentException {
        InputStream inputStream = null;
        try {
            inputStream = IOUtil.getInputStream(path);
            parse(inputStream, encoding);
        } finally {
            if (inputStream != null) {
                IOUtils.closeQuietly(inputStream);
            }
        }
    }

    public void parse(String text) throws DocumentException {
        Document document = Dom4JReader.getDocument(text);

        parse(document);
    }

    public void parse(File file, String encoding) throws DocumentException, IOException, UnsupportedEncodingException {
        Document document = Dom4JReader.getDocument(file, encoding);

        parse(document);
    }

    public void parse(InputStream inputStream, String encoding) throws DocumentException, IOException {
        Document document = Dom4JReader.getDocument(inputStream, encoding);

        parse(document);
    }

    public void parse(InputSource inputSource, String encoding) throws DocumentException, IOException {
        Document document = Dom4JReader.getDocument(inputSource, encoding);

        parse(document);
    }

    public void parse(Reader reader, String encoding) throws DocumentException, IOException {
        Document document = Dom4JReader.getDocument(reader, encoding);

        parse(document);
    }

    public void parse(URL url, String encoding) throws DocumentException, IOException {
        Document document = Dom4JReader.getDocument(url, encoding);

        parse(document);
    }

    public void parse(Document document) {
        Element rootElement = document.getRootElement();

        parseRoot(rootElement);
    }

    protected abstract void parseRoot(Element element);
}