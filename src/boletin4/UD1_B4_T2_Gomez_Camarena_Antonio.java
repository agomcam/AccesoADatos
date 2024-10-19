package boletin4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class UD1_B4_T2_Gomez_Camarena_Antonio {
    public static void main(String[] args) {
        File file = new File(System.getProperty("user.home"), "\\AD\\peliculas.xml");

        for (Pelicula pelicula : cargarPeliculas(file)) {

            System.out.println(pelicula);
            System.out.println("-------------------------------------");
        }
    }

    /**
     * Funcion que obtiene todas las peliculas que hay en un xml y lo añade a un
     * arrayList<Pelicula> que mas tarde devolvera
     * 
     * @param f - fichero xml donde se encuentran las peliculas
     * @return - arrayList<Pelicula>
     */
    public static ArrayList<Pelicula> cargarPeliculas(File f) {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(f);
            Element raiz = document.getDocumentElement();
            NodeList listaHijos = raiz.getChildNodes();

            // Recorremos la lista de nodos y lo añadimos a nuestro objeto de Pelicula
            for (int i = 0; i < listaHijos.getLength(); i++) {
                Node nodoAux = listaHijos.item(i);

                if (nodoAux.getNodeType() == Element.ELEMENT_NODE) {
                    Element elementAux = (Element) nodoAux;

                    // Creamos el objeto y lo añadimos a su correspondiente lista
                    Pelicula pelicula = new Pelicula();

                    pelicula.setTitulo(elementAux.getElementsByTagName("Titulo").item(0).getTextContent());
                    pelicula.setDirector(elementAux.getElementsByTagName("Director").item(0).getTextContent());
                    pelicula.setDuracion(
                            Integer.valueOf(elementAux.getElementsByTagName("Duracion").item(0).getTextContent()));
                    pelicula.setGenero(elementAux.getElementsByTagName("Genero").item(0).getTextContent());
                    pelicula.setFecha(
                            Integer.valueOf(elementAux.getElementsByTagName("Fecha").item(0).getTextContent()));

                    if (elementAux.getElementsByTagName("sinopsis").item(0) != null) {
                        pelicula.setSinopsis(elementAux.getElementsByTagName("sinopsis").item(0).getTextContent()
                                .replaceAll("\\n\\r?", ""));
                    }
                    ArrayList<String> actores = new ArrayList<>();
                    for (int j = 0; j < elementAux.getElementsByTagName("Actor").getLength(); j++) {
                        actores.add(elementAux.getElementsByTagName("Actor").item(j).getTextContent());
                    }
                    pelicula.setActores(actores);
                    listaPeliculas.add(pelicula);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        return listaPeliculas;
    }
}
