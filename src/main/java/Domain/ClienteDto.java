package Domain;
/**
 * Modelo para la conexion a la base de datos "clientes".
 *
 * @author santiago.alvarezp@udea.edu.co
 *
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteDto implements Serializable {

    private static final long serialVersionUID = 5759483207455904078L;

    private Integer idCliente;
    private String nombres;
    private String apellidos;
    private String docType;
    private Integer edad;
    private String ciudad;

}
