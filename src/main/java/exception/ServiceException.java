package exception;

/**
 * Clase que define un objeto de excepcion generico para todos las Apis
 *
 * @author santiago.alvarezp@udea.edu.co
 *
 */
public class ServiceException extends RuntimeException {


    private static final long serialVersionUID = -958060418553191973L;
    /**
     * Valor que permite definir un estado para la respuesta HTTP
     */
    private final int status;
    /**
     * Codigo unico que identifique la respuesta
     */
    private final String code;

    /**
     * Constructor para la creacion de excepciones personalizadas sin causa raiz
     *
     * @param status estatus
     * @param code código
     * @param message mensaje
     */
    public ServiceException(int status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }

    /**
     * Constructor para la creacion de excepciones personalizadas
     *
     * @param status
     *            Estado HTTP
     * @param code
     *            Codigo de respuesta unico
     * @param message
     *            Mensaje con el detalle del error
     * @param e
     *            Objeto con la información de la cauza raiz de la excepcion
     */
    public ServiceException(int status, String code, String message, Throwable e) {
        super(message, e);
        this.status = status;
        this.code = code;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

}
