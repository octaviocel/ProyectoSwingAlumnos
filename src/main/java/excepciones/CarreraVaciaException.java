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
public class CarreraVaciaException extends Exception {

    /**
     * Creates a new instance of <code>CarreraVaciaException</code> without
     * detail message.
     */
    public CarreraVaciaException() {
    }

    /**
     * Constructs an instance of <code>CarreraVaciaException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CarreraVaciaException(String msg) {
        super(msg);
    }
}
