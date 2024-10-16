package boletin4;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.XMLReader;

public class UD1_B4_T6_Gomez_Camarena_Antonio {
    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(new GestionSAXNoticias());
           reader.parse(System.getProperty("user.home") + "\\AD\\rss.aspx.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
