package pruebaExamen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class Hotel implements Serializable {
	private static final long serialVersionUID = 1L;
	private static List<Reserva> listaReservas;
	private static Properties configuracion;

	public static void main(String[] args) {
		// Cargar la configuracion (1)
		cargarConfiguracion();

		if (configuracion != null) {
			System.out.println("Configuracion cargada con exito");

			// Crear unas reservas de ejemplo
			listaReservas = new ArrayList<Reserva>();
			listaReservas.add(new Reserva("Ana Amarillo", 1, new Fecha(1, 10, 2022), new Fecha(5, 10, 2022)));
			listaReservas.add(new Reserva("Bob", 1, new Fecha(12, 12, 2022), new Fecha(14, 12, 2022)));
			listaReservas.add(new Reserva("Ana Amarillo", 1, new Fecha(12, 12, 2022), new Fecha(14, 12, 2022)));
			listaReservas.add(new Reserva("Daniel Duran Duran", 2, new Fecha(1, 12, 2022), new Fecha(2, 12, 2022)));
			listaReservas.add(new Reserva("Carla Cañas Cabrera", 30, new Fecha(1, 12, 2022), new Fecha(10, 12, 2022)));
			System.out.println("Se han anadido 5 reservas");

			// Guardar clientes en txt (2)
			guardarClientesEnTXT(); // Debe haber creado el archivo clientes.txt

			// Guardar las reservas en binario (3)
			guardarReservasBinairio(); // Debe haber creado el archivo reservas.dat con 5 reservas

			// Anadir una reserva mas para diferenciar los archivos
			listaReservas.add(new Reserva("Ana Amarillo", 5, new Fecha(12, 12, 2023), new Fecha(1, 1, 2024)));
			System.out.println("Se ha anadido una reservas mas");

			// Cargar las reservas binario (4)
			cargarReservasBinario(); // Debe haber actualizado la lista con 5 reservas
			if (listaReservas.size() == 5)
				System.out.println("Se ha desecho el ultimo cambio");

			// Guardar las reservas en XML (5)
			guardarReservasXMLDOM(); // Debe haber creado el archivo reservas.xml con 5 reservas

			// Leer el XML guardado (6)
			System.out.println("---Reservas del Hotel---");
			leerReservasXMLSAX(); // Muestra por pantalla las 5 reservas

		} else {
			System.err.println("No se ha cargado la configuracion correctamente");
		}
	}

	// 0. (0.5 pts) Además, las clases Reserva y Fecha están incompletas.

	// 1. void cargarConfiguracion() (1pts)
	private static void cargarConfiguracion() {
		File file = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "AD"
				+ System.getProperty("file.separator") + "UD1_PruebaHotel" + System.getProperty("file.separator")
				+ "confHotel2.ini");
		System.out.println(file.exists());
		configuracion = new Properties();
		FileReader fr = null;
		if (file.canRead()) {
			try {
				fr = new FileReader(file);
				configuracion.load(fr);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println("La configuración se ha cargado correctamente");
				try {
					fr.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	// 2. void guardarClientesenTXT() (1.75pts)
	private static void guardarClientesEnTXT() {
		Set<String> contNombres = new TreeSet<>(); // este contenedor ordena y comprueba que no hay datos repetidos
		BufferedWriter bw = null;
		File file = new File(System.getProperty("user.home"), configuracion.getProperty("textF"));
		try {
			bw = new BufferedWriter(new FileWriter(file));
			// Obtenemos todos los nombres de los clientes
			for (Reserva nombre : listaReservas) {
				contNombres.add(nombre.getCliente());
			}

			// Escribimos los datos en el fichero

			for (String string : contNombres) {

				bw.write(string);
				bw.newLine();

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("------Creado el archivo con exito------");
			try {

				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// 3. void guardarReservasBinairio() (1pts)
	private static void guardarReservasBinairio() {
		File file = new File(System.getProperty("user.home"), configuracion.getProperty("binF"));

		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(listaReservas);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("------Se ha creado el archivo y puede ser recuperado por el siguiente metodo------");
			try {
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 4. void cargarReservasBinario() (1.25 pts)
	@SuppressWarnings("unchecked")
	private static void cargarReservasBinario() {
		File file = new File(System.getProperty("user.home"), configuracion.getProperty("binF"));

		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			listaReservas = (List<Reserva>) ois.readObject();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("------He conseguido deshacer el ultimo cambio, de añadir una reserva más------");
			try {
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 5. void guardarReservasXMLDOM() (3 pts)
	private static void guardarReservasXMLDOM() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, "reservas", null);
			document.setXmlVersion("1.0");
			document.setXmlStandalone(false);
			Element raiz = document.getDocumentElement();

			// Recorremos todos los elementos del array para añadir a cada uno de sus
			// elementos al xml correspondiente
			for (Reserva reserva : listaReservas) {
				Element elementReserva = document.createElement("reserva");
				anadeElemento(document, elementReserva, "cliente", reserva.getCliente());
				anadeElemento(document, elementReserva, "habitacion", String.valueOf(reserva.getNumHabitacion()));
				Element fechaInicio = document.createElement("fechaInicio");
				anadeElemento(document, fechaInicio, "dia", String.valueOf(reserva.getFechaInicio().getDia()));
				anadeElemento(document, fechaInicio, "mes", String.valueOf(reserva.getFechaInicio().getMes()));
				anadeElemento(document, fechaInicio, "anyo", String.valueOf(reserva.getFechaInicio().getAnyo()));
				elementReserva.appendChild(fechaInicio);
				Element fechaFin = document.createElement("fechaFin");
				anadeElemento(document, fechaFin, "dia", String.valueOf(reserva.getFechaFin().getDia()));
				anadeElemento(document, fechaFin, "mes", String.valueOf(reserva.getFechaFin().getMes()));
				anadeElemento(document, fechaFin, "anyo", String.valueOf(reserva.getFechaFin().getAnyo()));
				elementReserva.appendChild(fechaFin);
				raiz.appendChild(elementReserva);
			}

			// Guardamos los datos en el xml

			File file = new File(System.getProperty("user.home"), configuracion.getProperty("xmlF"));
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(file);
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Se ha creado el documento XML y tiene el contenido de las reservas");
		}
	}

	// 6. void leerReservasXMLSAX() (1.5 pts)
	private static void leerReservasXMLSAX() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			XMLReader reader = parser.getXMLReader();

			reader.setContentHandler(new DefaultHandler() {
				boolean nombre = false;
				boolean habitacion = false;
				boolean fechaInicio = false;
				boolean fechaFin = false;
				boolean dia = false;
				boolean mes = false;
				boolean anyo = false;

				@Override
				public void startDocument() throws SAXException {
					System.out.println("---Reservas del hotel---");
				}

				@Override
				public void endDocument() throws SAXException {
					System.out.println("---Se muestran por pantalla las reservas---");
				}

				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					switch (qName) {
						case "cliente":
							nombre = true;
							break;
						case "habitacion":
							habitacion = true;
							break;
						case "fechaInicio":
							fechaInicio = true;
							break;
						case "dia":
							dia = true;
							break;
						case "mes":
							mes = true;
							break;
						case "anyo":
							anyo = true;
							break;
						case "fechaFin":
							fechaFin = true;
							break;
					}
				}

				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException {
					switch (qName) {
						case "cliente":
							nombre = false;
							break;
						case "habitacion":
							habitacion = false;
							break;
						case "fechaInicio":
							fechaInicio = false;
							break;
						case "dia":
							dia = false;
							break;
						case "mes":
							mes = false;
							break;
						case "anyo":
							anyo = false;
							break;
						case "fechaFin":
							fechaFin = false;
							break;
					}
				}

				@Override
				public void characters(char[] ch, int start, int length) throws SAXException {
					if (nombre) {
						System.out.println("-Reserva-");
						System.out.println("Cliente: " + new String(ch, start, length));
					} else if (habitacion) {
						System.out.println("Habitación: " + new String(ch, start, length));

					} else if (fechaInicio && dia) {
						System.out.print("Fecha Inicio: " + new String(ch, start, length) + "/");
					} else if (fechaInicio && mes) {
						System.out.print(new String(ch, start, length) + "/");
					} else if (fechaInicio && anyo) {
						System.out.println(new String(ch, start, length));
					} else if (fechaFin && dia) {
						System.out.print("Fecha Fin: " + new String(ch, start, length) + "/");
					} else if (fechaFin && mes) {
						System.out.print(new String(ch, start, length) + "/");
					} else if (fechaFin && anyo) {
						System.out.println(new String(ch, start, length));
						System.out.println();
					}
				}
			});

			reader.parse(System.getProperty("user.home") + configuracion.getProperty("xmlF"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// METODOS GENERICOS PARA TRABAJAR CON DOM
	private static Element anadeElemento(Document doc, Element raiz, String clave, String valor) {
		// Creamos un elemento [clave]
		Element e = doc.createElement(clave); // <[clave]> ... </[clave]>

		// Añadimos el texto
		anadeElemento(doc, e, valor); // <[clave]> [valor] </[clave]>

		// Pegamos el elemento a la raiz
		raiz.appendChild(e);
		return e;
	}

	private static Element anadeElemento(Document doc, Element raiz, String valor) {
		// Pegamos el texto a la raiz
		raiz.appendChild(doc.createTextNode(valor)); // <[raiz]> [valor] </[raiz]>
		return raiz;
	}

}
