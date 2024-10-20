package boletin4;

import java.io.File;
import java.time.LocalDate;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UD1_B4_T3_Gomez_Camarena_Antonio {
    public static void main(String[] args) {
        File file = new File(System.getProperty("user.home"), "\\AD\\productosAntiguos.xml");
        File fileAux = new File(file.getPath().split("productosAntiguos.xml")[0], "productosAgotados.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            // leer
            Document document = builder.parse(file);
            Element element = document.getDocumentElement();
            NodeList listaHijos = element.getChildNodes();

            // Escribir
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document2 = implementation.createDocument(null, "ListaProductos", null);
            document2.setXmlVersion("1.0");
            document2.setXmlStandalone(false);
            Element elementoRaiz = document2.getDocumentElement();
            elementoRaiz.setAttribute("fecha", LocalDate.now().toString());

            for (int i = 0; i < listaHijos.getLength(); i++) {
                Node nodoAux = listaHijos.item(i);
                if (nodoAux.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementAux = (Element) nodoAux;
                    String nombre = elementAux.getElementsByTagName("Nombre").item(0).getTextContent();
                    String precio = elementAux.getElementsByTagName("Precio").item(0).getTextContent();
                    String stock = elementAux.getElementsByTagName("Stock").item(0).getTextContent();

                    if (Integer
                            .valueOf(elementAux.getElementsByTagName("Stock").item(0).getTextContent().strip()) < 5) {
                        elementAux.setAttribute("aLaVenta", "false");
                    }

                    anadirProducto(document2, elementoRaiz, elementAux.getAttribute("id"),
                            elementAux.getAttribute("aLaVenta"), nombre, precio, stock);

                }
            }

            // transformamos en DOOM en un archivo de guardar
            DOMSource source = new DOMSource(document2); // origen DOOM
            StreamResult result = new StreamResult(fileAux); // Destino del archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("Terminado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void anadirProducto(Document _document, Element _raiz, String _id, String _aLaVenta, String _nombre,
            String _precio, String _stock) {
        // Creamos un elemento Producto
        Element producto1 = _document.createElement("Producto"); // <Producto> ... </Producto>

        producto1.setAttribute("aLaVenta", _aLaVenta);
        producto1.setAttribute("id", _id);

        // Creamos los elementos Nombre y Precio
        Element Nombre1 = _document.createElement("Nombre"); // <Nombre> ... </Nombre>
        Element Precio1 = _document.createElement("Precio"); // <Precio> ... </Precio>
        Element Stock = _document.createElement("Stock"); // <Stock> ... </Stock>

        // Añadimos el texto a los elementos
        Nombre1.appendChild(_document.createTextNode(_nombre));
        Precio1.appendChild(_document.createTextNode(_precio));
        Stock.appendChild(_document.createTextNode(_stock));

        // Pegamos el nombre y el precio al producto
        producto1.appendChild(Nombre1);
        producto1.appendChild(Precio1);

        // Pegamos el producto a la raiz
        _raiz.appendChild(producto1);
    }
}
