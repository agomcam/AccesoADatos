package boletin4;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

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
            reader.setContentHandler(new DefaultHandler() {
                boolean tituloPelicul = false;
                boolean anioPelicula = false;
                boolean director = false;
                boolean actor = false;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes)
                        throws SAXException {
                    super.startElement(uri, localName, qName, attributes);
                    switch (qName) {
                        case "Titulo":
                            tituloPelicul = true;
                            break;
                        case "Fecha":
                            anioPelicula = true;
                            break;
                        case "Director":
                            director = true;
                            break;
                        case "Actor":
                            actor = true;
                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    super.endElement(uri, localName, qName);
                    switch (qName) {
                        case "Titulo":
                            tituloPelicul = false;
                            break;
                        case "Fecha":
                            anioPelicula = false;
                            break;
                        case "Director":
                            director = false;
                            break;
                        case "Actor":
                            actor = false;
                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    super.characters(ch, start, length);
                    if (tituloPelicul) {
                        System.out.println("Titulo pelicula - " + new String(ch, start, length));
                    } else if (anioPelicula) {
                        System.out.println("Año pelicula - " + new String(ch, start, length));
                    } else if (director) {
                        System.out.println("Director - " + new String(ch, start, length));
                        System.out.println("-----------------------------------------------");
                    } else if (actor) {
                        System.out.println("Actor - " + new String(ch, start, length));
                    }
                }
            });
            reader.parse(System.getProperty("user.home") + "\\AD\\peliculas.xml");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
