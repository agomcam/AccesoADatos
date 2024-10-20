package boletin4;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class UD1_B4_T4_Gomez_Camarena_Antonio {
    public static void main(String[] args) {
        List<Ingrediente> listIngredientes = new ArrayList<>();
        listIngredientes.add(new Ingrediente("Pollo", "750gr"));
        listIngredientes.add(new Ingrediente("Especias", "una pizca"));
        Receta receta = new Receta("Pollo al chilindrón", listIngredientes, "Asar el pollo y echarle especias",
                "30 min");
        // 1.Instanciar el DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            // 2.Crear un DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // 3.Crear un DOMImplementation
            DOMImplementation implementation = builder.getDOMImplementation();
            // 4.Crear el Document vacio
            Document document = implementation.createDocument(null, "Receta", null);
            // 5.Escribir la información en el Document
            document.setXmlVersion("1.0");
            document.setXmlStandalone(false);

            // Nodo raiz del documento
            Element raiz = document.getDocumentElement();

            // añadimos el titulo
            anadeElemento(document, raiz, "Titulo", receta.getTitulo());

            // creo el elemento ingrediente
            Element ingredientes = document.createElement("ingredientes");

            // creo cada ingrediente y añado el contenido a su interior y su atributos

            for (Ingrediente i : receta.getIngredientes()) {
                // indicamos cual es el nombre
                Element e = anadeElemento(document, ingredientes, "ingrediente", i.getNombreIngrediente());
                // indicamos cual es la cantidad
                e.setAttribute("cantidad", i.getCantidad());
            }
            // Añadimos el ingrediente que acabamos de crear a la raiz de nuestro documento
            raiz.appendChild(ingredientes);
            // Añadimos el procedimiento
            anadeElemento(document, raiz, "procedimiento", receta.getProcedimiento());
            // Añadimos el tiempo
            anadeElemento(document, raiz, "tiempo", receta.getTiempo());

            // Transformamos el dom para guardar el elemento
            File file = new File(System.getProperty("user.home") + "\\AD\\recetaJava.xml");
            // Origen del DOM
            DOMSource source = new DOMSource(document);
            // indicamos el destino donde queremos guardar el archivo
            StreamResult result = new StreamResult(file);
            // creamos el transform
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            // indicamos que queremos que tenga el formato formateado
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            // iniciamos la transformacion del documento
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Element anadeElemento(Document doc, Element raiz, String clave, String valor) {
        // Creamos un elemento [clave]
        Element e = doc.createElement(clave); // <[clave]> ... </[clave]>

        // Añadimos el texto
        anadeNodoTextual(doc, e, valor); // <[clave]> [valor] </[clave]>

        // Pegamos el elemento a la raiz
        raiz.appendChild(e);
        return e;
    }

    private static Element anadeNodoTextual(Document doc, Element raiz, String valor) {
        // Pegamos el elemento a la raiz
        raiz.appendChild(doc.createTextNode(valor)); // <[raiz]> [valor] </[raiz]>
        return raiz;
    }
}
