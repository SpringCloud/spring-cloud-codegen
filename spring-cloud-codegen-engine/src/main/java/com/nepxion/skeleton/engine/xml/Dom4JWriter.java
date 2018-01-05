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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Dom4JWriter {
    public static Document createDocument() {
        return DocumentHelper.createDocument();
    }

    public static String getText(Document document, String encoding) throws IOException, UnsupportedEncodingException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputFormat outputFormat = new OutputFormat("  ", true, encoding);

        try {
            XMLWriter writer = new XMLWriter(baos, outputFormat);
            writer.write(document);

            baos.flush();
        } catch (UnsupportedEncodingException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            if (baos != null) {
                baos.close();
            }
        }

        return baos.toString(encoding);
    }
}