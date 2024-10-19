package boletin4;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class UD1_B4_T1_Gomez_Camarena_Antonio {

    // Mostrar titulo pelicula, año, actores y director
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

            // Obtenemos los nodos hijos
            NodeList listaNodosHijos = raiz.getChildNodes();

            for (int i = 0; i < listaNodosHijos.getLength(); i++) {
                Node nodoAux = listaNodosHijos.item(i);

                // Si el hijo es un elemento, hacemos el casting de este
                if (nodoAux.getNodeType() == Node.ELEMENT_NODE) {
                    // Hacemos el casting
                    Element elementaux = (Element) nodoAux;

                    System.out.println("---------------------------------------");
                    // Obtenemos los datos de los elementos
                    System.out.println(
                            "Titulo pelicula: " + elementaux.getElementsByTagName("Titulo").item(0).getTextContent());
                    System.out.println(
                            "Año de salida: " + elementaux.getElementsByTagName("Fecha").item(0).getTextContent());
                    // Recorremos todos los autores

                    for (int j = 0; j < elementaux.getElementsByTagName("Actor").getLength(); j++) {
                        System.out.println("Actor: " + elementaux.getElementsByTagName("Actor").item(j).getTextContent());
                    }

                    System.out.println(
                            "Director: " + elementaux.getElementsByTagName("Director").item(0).getTextContent());

                    System.out.println("---------------------------------------");
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

}
