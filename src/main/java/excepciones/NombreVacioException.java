/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

/**
 *
 * @author DELL
 */
public class NombreVacioException extends Exception {

    /**
     * Creates a new instance of <code>NombreVacioException</code> without
     * detail message.
     */
    public NombreVacioException() {
    }

    /**
     * Constructs an instance of <code>NombreVacioException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NombreVacioException(String msg) {
        super(msg);
    }
}
