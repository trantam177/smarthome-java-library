/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.util.string;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author michael
 */
public class XMLUtil {

    public static String XPathValueFromString(String sIn, String sxpath) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        DocumentBuilderFactory domFactory =
                DocumentBuilderFactory.newInstance();
        Document doc = loadXMLFromString(sIn);
        XPath xPath = XPathFactory.newInstance().newXPath();
        // XPath Query for showing all nodes value
        XPathExpression expr = xPath.compile(sxpath);

        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        String sReturn = "";
        for (int i = 0; i < nodes.getLength(); i++) {
            sReturn = nodes.item(i).getNodeValue();
        }
        return sReturn;
    }
    public static Document loadXMLFromString(String xml) throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        Document doc = builder.parse(is);
        return doc;
    }
}
