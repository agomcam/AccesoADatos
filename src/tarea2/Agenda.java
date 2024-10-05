package tarea2;

import java.io.Serializable;
import java.util.Arrays;

public class Agenda implements Serializable {
    private static final long serialVersionUID = 1L;
    Contacto contacto[];

    public Agenda() {
        contacto = new Contacto[5];
    }

    /**
     * Contrutor de agenda con todos los parametros
     * 
     * @param contacto - Array de contactos
     */
    public Agenda(Contacto[] contacto) {
        for (Contacto contacto2 : contacto) {
            annadeContacto(contacto2);
        }
    }

    public Contacto[] getContacto() {
        return contacto;
    }

    public void setContacto(Contacto[] contacto) {
        this.contacto = contacto;
    }

    /**
     * Funcion que sirve para añadir un contacto a nuestra agenda
     * 
     * @param contacto - contacto que queremos añadir nuevo
     * @return
     */
    public int annadeContacto(Contacto contacto) {
        // Recorremos todos los contactos para comprobar si ya existe
        for (int j = 0; j < this.contacto.length; j++) {
            if (this.contacto[j] != null && this.contacto[j].equals(contacto)) {
                System.out.println("El contacto ya existe y está en la posición: " + j);
                return j; // Contacto ya existente, no se añade
            }
        }

        // Verificamos si hay hueco para añadir un contacto nuevo
        for (int i = 0; i < this.contacto.length; i++) {
            if (this.contacto[i] == null) {
                this.contacto[i] = contacto;
                System.out.println("Contacto añadido exitosamente en la posición: " + i);
                return i; // Contacto añadido
            }
        }

        // Si no hay espacio, agrandamos la agenda
        Contacto[] contAux = this.contacto;
        this.contacto = new Contacto[this.contacto.length + 5];
        System.arraycopy(contAux, 0, this.contacto, 0, contAux.length);

        // Añadimos el contacto nuevo en la primera posición vacía
        this.contacto[contAux.length] = contacto;
        System.out.println("Hemos agrandado la agenda y añadido el contacto en la posición: " + contAux.length);
        return contAux.length;
    }

    /**
     * Funcion que sirve para elminar un contacto, en caso de ser eliminado
     * devolvera true en caso contraro false
     * 
     * @param contacto - contacto a eliminar
     * @return
     */
    public boolean elimarContacto(Contacto contacto) {

        for (Contacto cont : this.contacto) {
            if (cont.equals(contacto)) {
                System.out.println("Contacto eliminado correctamente");
                cont = null;
            }
        }

        return false;
    }

    /**
     * Funcion que sirve para mostrar todos los contactos que tenemos almacenados
     */
    public void mostrarContactos() {
        for (Contacto contacto2 : contacto) {
            System.out.println(contacto2);
        }
    }

    @Override
    public String toString() {
        return "Agenda [contacto=" + Arrays.toString(contacto) + "]";
    }
}
