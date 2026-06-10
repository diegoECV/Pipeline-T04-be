package vallegrande.edu.pe.visons.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientRequestTransactionRequest {

    @NotBlank(message = "username es requerido")
    @Size(min = 3, max = 50, message = "username debe tener entre 3 y 50 caracteres")
    private String username;

    @NotBlank(message = "firstName es requerido")
    @Size(min = 2, max = 100, message = "firstName debe tener entre 2 y 100 caracteres")
    private String firstName;

    @NotBlank(message = "lastName es requerido")
    @Size(min = 2, max = 100, message = "lastName debe tener entre 2 y 100 caracteres")
    private String lastName;

    @NotBlank(message = "companyName es requerido")
    @Size(min = 2, max = 200, message = "companyName debe tener entre 2 y 200 caracteres")
    private String companyName;

    @NotBlank(message = "taxId es requerido")
    @Pattern(regexp = "^\\s*$|^[0-9]{8,20}$", message = "taxId debe contener solo numeros entre 8 y 20 digitos")
    private String taxId;

    @Size(max = 100, message = "country debe tener como maximo 100 caracteres")
    private String country;

    @NotBlank(message = "email es requerido")
    @Email(message = "email debe tener un formato valido")
    @Size(max = 150, message = "email debe tener como maximo 150 caracteres")
    private String email;

    @Pattern(regexp = "^\\s*$|^[0-9+()\\-\\s]{7,20}$", message = "phone debe contener solo numeros y simbolos validos")
    private String phone;

    @Size(max = 255, message = "address debe tener como maximo 255 caracteres")
    private String address;

    private Integer ubigeoId;

    @Size(max = 500, message = "comments debe tener como maximo 500 caracteres")
    private String comments;
}
