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
public class ApellidoVacioException extends Exception {

    /**
     * Creates a new instance of <code>ApellidoVacioException</code> without
     * detail message.
     */
    public ApellidoVacioException() {
    }

    /**
     * Constructs an instance of <code>ApellidoVacioException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ApellidoVacioException(String msg) {
        super(msg);
    }
}
