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
public class DoubleException extends Exception {

    /**
     * Creates a new instance of <code>DoubleException</code> without detail
     * message.
     */
    public DoubleException() {
    }

    /**
     * Constructs an instance of <code>DoubleException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public DoubleException(String msg) {
        super(msg);
    }
}
