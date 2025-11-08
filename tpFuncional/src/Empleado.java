import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Empleado {
    private String nombre;
    private String departamento;
    private double salario;
    private int edad;
}
