package boletin4;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestorSAXPeliculasSelectivo extends DefaultHandler {
    boolean tituloPelicul = false;
    boolean anioPelicula = false;
    boolean director = false;
    boolean actor = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
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
        }else if (actor) {
            System.out.println("Actor - " + new String(ch, start, length));
        }
    }
}
