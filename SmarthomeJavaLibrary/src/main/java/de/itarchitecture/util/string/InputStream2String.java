/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.util.string;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

/**
 *
 * @author michael
 */
public class InputStream2String {

    public static String copyFromInputStream(InputStream in, String encoding) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtils.copy(in, writer, encoding);
        String outString = writer.toString();
        return outString;
    }
}
