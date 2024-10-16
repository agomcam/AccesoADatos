package boletin4;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class UD1_B4_T5_Gomez_Camarena_Antonio {
    public static void main(String[] args) {

        // Instanciamos el SAXParserFactory
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            // Creaos el SAXParser
            SAXParser parser = factory.newSAXParser();
            // Creamos el XMLReader
            XMLReader reader = parser.getXMLReader();
            // Implementamos el defaultHandler y configuramos el XMLReader
            reader.setContentHandler(new GestorSAXPeliculasSelectivo());
            reader.parse(System.getProperty("user.home") + "\\AD\\peliculas.xml");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
