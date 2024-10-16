package boletin4;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class UD1_B4_T1_Gomez_Camarena_Antonio {

    public static void main(String[] args) {
        File file = new File(System.getProperty("user.home"), "\\AD\\peliculas.xml");
        // 1. instanciamos el DocumentBuilderFactory 
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            // 2. creamos un document builder
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();

            // 3. Crear el document a partir del archivo
            Document document = documentBuilder.parse(file);
            // 4 leer la informacion del documento
            Element raiz = document.getDocumentElement(); // nodo raiz del documento
            System.out.println("El nodo raiz se llama: " + raiz.getNodeName());
            System.out.println("Tiene el texto: " + raiz.getTextContent());

            NodeList listaNodoRaiz = raiz.getChildNodes(); // obtenemos los hijos de la raiz
            System.out.println("Tiene un total de: " + listaNodoRaiz.getLength() + " hijos");

                
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        System.out.println(file.exists());
    }

}
