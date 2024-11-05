package examenT1;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class Principal_Gomez_Camarena_Antonio {
	private static File home = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "AD"
			+ System.getProperty("file.separator") + "UD1_PruebaComercial"); // user.home\AD\UD1_PruebaComercial
	private static Properties configuracion = init();
	private static Set<Comercial> comerciales = new HashSet<Comercial>();
	private static Set<Empresa> empresas = new HashSet<Empresa>();
	private static Set<Visita> visitas = new HashSet<Visita>();

	public static void main(String[] args) {
		System.out.println("-- -- INICIO PRINCIPAL -- --");

		// Datos por defecto
		Comercial c1 = new Comercial("Ana Anaya");
		Comercial c2 = new Comercial("Bob Borger");
		comerciales.add(c1);
		comerciales.add(c2);

		Empresa e1 = new Empresa(8587, "InfoTecno");
		Empresa e2 = new Empresa(4245, "TecnoPro");
		empresas.add(e1);
		empresas.add(e2);

		Visita v1 = new Visita(c1, new Date(2020 - 1900, 1, 7, 10, 30), e1);
		Visita v2 = new Visita(c1, new Date(2020 - 1900, 1, 1, 11, 00), e2);
		Visita v3 = new Visita(c2, new Date(), e2); // Ahora
		visitas.add(v1);
		visitas.add(v2);
		visitas.add(v3);

		System.out.println("Se han cargado los datos por defecto");

		System.out.println("Intentado cargar los comerciales desde fichero");
		cargarComerciales_Binario();
		System.out.println("Ahora hay: " + comerciales.size() + " comerciales");

		System.out.println("Intentado añadir un nuevo comercial");
		System.out.print("Introduce el nombre el nuevo comercial | Pulsa Intro para saltar este paso: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		if (name.length() != 0) {
			annadeComerciales_Binario(new Comercial(name));
			System.out.println("Ahora hay: " + comerciales.size() + " comerciales");
		} else {
			System.out.println("No se ha añadido un nuevo comercial");
		}

		System.out.println("Intentado cargar las empresas desde fichero usando SAX");
		cargarEmpresas_XML_SAX();
		System.out.println("Ahora hay: " + empresas.size() + " empresas");

		System.out.println("Intentado generar el fichero de XML con las visitas usando DOM");
		guardarVisitasDOM();

		System.out.println("Intentado generar el fichero de texto con las visitas");
		guardarVisitas_TXT();

		System.out.println("-- -- FIN PRINCIPAL -- --");

	}

	/*
	 * 0. (0.5 pts) Las clases Comercial, Visita y Empresa están incompletas. Añade
	 * lo necesario para que puedan ser almacenadas en archivos binarios. Comenta en
	 * el código las modificaciones necesarias
	 * 
	 * // he puesto para que implemente de Serializable y le he añadido una variable
	 * de id
	 * 
	 * /*
	 * 
	 */
	public static Properties init() {
		System.out.println("-- INIT() --");
		File fichero = new File(home, "config.ini");
		Properties propierties = new Properties();
		if (fichero.exists()) {
			try {
				propierties.load(new FileReader(fichero));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				propierties.setProperty("file_xml_Empresas", "empresas.xml");
				propierties.setProperty("file_dat_Comerciales", "comerciales.dat");
				propierties.setProperty("file_txt_Visitas", "visita.txt");
				propierties.setProperty("file_xml_Visitas", "visita.xml");

				propierties.store(new FileWriter(fichero), "Configuracion de la aplicacion");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return propierties;
	}

	private static void cargarComerciales_Binario() {
		System.out.println("-- cargarComerciales_Binario() --");

		File file = new File(home, configuracion.getProperty("file_dat_Comerciales"));

		if (file.exists()) {
			// Leemos el fichero
			Set<Comercial> comercialesTemp = new HashSet<>();
			Comercial com;
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(new FileInputStream(file));
				// Obtenemos los comerciales
				while (true) {
					com = (Comercial) ois.readObject();
					comercialesTemp.add(com);
				}

			} catch (EOFException e) {
				System.out.println("Leido correctamente");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					ois.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// Reemplazamos nuestro array principal
			comerciales = comercialesTemp;
		} else {
			// creamos el fichero con los datos
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
				// Escribimos los comerciales
				for (Comercial comercial : comerciales) {
					oos.writeObject(comercial);
				}
				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("---- Se ha creado correctamente los comerciales ----");
		}

	}

	private static void annadeComerciales_Binario(Comercial c) {
		System.out.println("-- annadeComerciales_Binario(" + c + ") --");
		File file = new File(home, configuracion.getProperty("file_dat_Comerciales"));
		// Comprobamos que existe el archivo
		if (file.exists()) {
			ObjectOutputStream oos = null;

			try {
				oos = new ObjectOutputStream(new FileOutputStream(file, true)) {
					@Override
					protected void writeStreamHeader() throws IOException {

					}
				};
				System.out.println("--------------");
				for (Comercial comercial : comerciales) {
					System.out.println(comercial);
					System.out.println(comercial.equals(c));
				}
				System.out.println("--------------");
				System.out.println(!comerciales.contains(c));
				if (!comerciales.contains(c)) {
					oos.writeObject(c);
				} else {
					System.out.println("Ya existe este comercial");
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					oos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} 
	}

	private static void cargarEmpresas_XML_SAX() {
		System.out.println("-- cargarEmpresas_XML_SAX() --");

		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			XMLReader reader = parser.getXMLReader();

			reader.setContentHandler(new DefaultHandler() {
				boolean empresa = false;
				boolean cif = false;
				boolean nombre = false;

				Empresa empresaOj;

				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {

					switch (qName) {
						case "empresa":
							empresa = true;
							break;
						case "cif":
							cif = true;
							break;
						case "nombre":
							nombre = true;
							break;
						default:
							break;
					}
				}

				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException {
					switch (qName) {
						case "empresa":
							empresa = false;
							break;
						case "cif":
							cif = false;
							break;
						case "nombre":
							nombre = false;
							break;
						default:
							break;
					}
				}

				@Override
				public void characters(char[] ch, int start, int length) throws SAXException {
					if (empresa) {
						if (cif) {
							empresaOj = new Empresa();
							empresaOj.setCif(Integer.parseInt(new String(ch, start, length)));
						} else if (nombre) {
							empresaOj.setNombre(new String(ch, start, length));
							empresas.add(empresaOj);
						}
					}
				}
			});
			File file = new File(home, configuracion.getProperty("file_xml_Empresas"));
			System.out.println(file.exists());
			reader.parse(file.getAbsolutePath());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void guardarVisitasDOM() {
		System.out.println("-- guardarVisitas_TXT() --");
		File file = new File(home, configuracion.getProperty("file_xml_Visitas"));
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {

			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, "Visitas", null);
			document.setXmlVersion("1.0");
			document.setXmlStandalone(false);

			Element raiz = document.getDocumentElement();
			for (Visita v : visitas) {
				String fechaFormateada = formatoFecha.format(v.getFecha());
				Element visita = anadeElemento(document, raiz, "Visita", "");
				anadeElemento(document, visita, "Comercial", v.getComercial().getNombre());
				anadeElemento(document, visita, "Fecha", fechaFormateada);
				anadeElemento(document, visita, "Empresa", v.getEmpresa().getNombre());
			}

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(file);
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Se ha creado correctamente los archivos");

	}

	private static void guardarVisitas_TXT() {
		File file = new File(home, configuracion.getProperty("file_txt_Visitas"));

		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			for (Visita visita : visitas) {
				String fechaFormateada = formatoFecha.format(visita.getFecha());
				bw.write(fechaFormateada + " - " + visita.getComercial().getNombre() + " -> "
						+ visita.getEmpresa().getCif() + ":" + visita.getEmpresa().getNombre());
				bw.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	// METODOS GENERICOS PARA DOM
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
