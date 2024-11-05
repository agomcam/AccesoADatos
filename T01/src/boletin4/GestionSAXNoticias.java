package boletin4;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionSAXNoticias extends DefaultHandler {
    boolean titulo = false;
    boolean fecha = false;
    boolean item = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch (qName) {
            case "title":
                titulo = true;
                break;
            case "pubDate":
                fecha = true;
                break;
            case "item":
                item = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        switch (qName) {
            case "title":
                titulo = false;
                break;
            case "pubDate":
                fecha = false;
                break;
            case "item":
                item = false;
                break;
            default:
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (item && titulo) {
            System.out.println("Titulo - " + new String(ch, start, length));
        } else if (item && fecha) {
            System.out.println("Fecha - " + new String(ch, start, length));
            System.out.println("---------------------------------------------");
        }
    }
}
