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
import java.io.Writer;

public class CharacterCaseWriter extends Writer {
    private Writer out;
    private boolean upperCase = true;

    public CharacterCaseWriter(Writer out, boolean upperCase) {
        this.out = out;
        this.upperCase = upperCase;
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        if (upperCase) {
            cbuf[0] = Character.toUpperCase(cbuf[0]);
        } else {
            cbuf[0] = Character.toLowerCase(cbuf[0]);
        }

        out.write(String.valueOf(cbuf).trim());
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}