import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Libro {
    public String titulo;
    public String autor;
    public int paginas;
    public double precioLibro;

    public Libro(String titulo, String autor, int paginas, double precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
        this.precioLibro = precioLibro;
    }
}
