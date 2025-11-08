import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("EJERCICIO 1");
        List<Alumno> alumnos = List.of(
                new Alumno("ale", 20, "economia"),
                new Alumno("leo", 10, "biologia"),
                new Alumno("biank", 9, "matenatica"),
                new Alumno("pepi", 7.3, "matenatica"),
                new Alumno("juan", 6.2, "economia"),
                new Alumno("chanchi", 8.6, "so")
        );
        List<String> aprobados = alumnos.stream()
                .filter(a -> a.getNota() >= 7 && a.getNota() <= 10)     //FILTRA los q cumplen la condicion
                .map(a -> a.getNombre().toUpperCase())// los muestra en mayuscula
                .sorted()//ordena lo filtrado
                .toList(); // coniverte un stream a lista
        System.out.println("alumnos aprobados mayor a 7 ");
        System.out.println(aprobados);

        double promedio = alumnos.stream()
                .mapToDouble(Alumno::getNota)       //calcula el promedio
                .average()
                .orElse(0);
        System.out.println("promedio de alumnos");
        System.out.println(promedio);

        Map<String, List<Alumno>> agrupados = alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));
        agrupados.forEach((curso, lista) -> {
            System.out.println(curso + ": " + lista);
        });
        System.out.println();

        List<Alumno> top3 = alumnos.stream()
                .sorted(Comparator.comparingDouble(Alumno::getNota).reversed())
                .limit(3)
                .toList(); //DEVULVE UNA LIST<t>
        System.out.println("TOP 3 mejores promedios de alumnos ");
        top3.forEach(System.out::println);


        System.out.println("EJERCICIO 2");
        List<Producto> productos = List.of(
                new Producto("azucar", "trigo", 100, 3),
                new Producto("harina", "trigo", 200, 45),
                new Producto("leche", "animal", 250, 98),
                new Producto("huevo", "animal", 500, 180));

        List<Producto> proXprecio = productos.stream()
                .filter(p -> p.getPrecio() > 100)
                .sorted(Comparator.comparing(Producto::getPrecio))
                .collect(toList());
        proXprecio.forEach(System.out::println);

        System.out.println("Cantidad de stock por categoria ");
        Map<String,Integer> stockXcategoria = productos.stream()
                .collect(Collectors.groupingBy(Producto::getCategoria,Collectors.summingInt(Producto::getStock)));
                stockXcategoria.forEach((categoria, stock) -> System.out.println(categoria + ":" + stock));

        System.out.println("Nombre y precio del Producto");
                String nombreCONprecio= productos.stream()
               .map(p-> p.getNombre()+ "" + "$" + p.getPrecio())
               .collect(Collectors.joining(";" ))   ;
        System.out.println(nombreCONprecio);

        System.out.println("Promedio de precio por categoría");

        double promedioGeneral = productos.stream()
                .collect(Collectors.averagingDouble(Producto::getPrecio));

        System.out.println("Precio promedio general: $" + promedioGeneral);

        Map<String, Double> promedioPORcategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)
                ));

        promedioPORcategoria.forEach((categoria, prom) -> {
            System.out.println(categoria + ": $" + promedio);
        });


        System.out.println("EJERCICIO 3");
        List<Libro> libros= List.of(
                new Libro("CASA RODANTE","Alejandra",500,1100),
                new Libro("FLORES AMARILLAS","Leonela",305,2055),
                new Libro("FUERZA DE LOS MONTERREY", "chanchi",200, 1136.32),
                new Libro("ESPEJO AFRICANO", "chanchi", 600,999));

        System.out.println("Libros con mas de 300 pag");
        List<String> pag= libros.stream()
                .filter(a->a.paginas>300)
                .sorted(Comparator.comparing(Libro::getTitulo))
                .map(Libro::getTitulo)
                .toList();
        System.out.println(pag);


        System.out.println("promedio de paginas de TODOS los libros");
        double promedioLibros= libros.stream()
                .collect(Collectors.averagingDouble(Libro::getPaginas));
        System.out.println(promedioLibros);

        System.out.println("Libros X Autor ");
        Map<String, Long> cantidadPorAutor = libros.stream()
                .collect(Collectors.groupingBy(Libro::getAutor, Collectors.counting()));

        cantidadPorAutor.forEach((autor, cantidad) -> {
            System.out.println(autor + " tiene " + cantidad + " libros.");
        });

        System.out.println("LIBRO MAS CARO");
        Optional<Libro> libroMasCaro = libros.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Libro::getPrecioLibro)));

        System.out.println(libroMasCaro);

        System.out.println("EJERCICIO 4");
        List<Empleado> empleados = List.of(
                new Empleado("ale","sistemas",6000.0,23),
                new Empleado("leo","recursos",8000.0,24),
                new Empleado("alice","sistemas",6000.0,24),
                new Empleado("pichita","recursos", 800,30),
                new Empleado("biank", "industria",1000,22)
        );

        System.out.println("Empleados con salrio mayor a 2000");
        List<Empleado> mayorSalario = empleados.stream()
                        .filter(a->a.getSalario()>2000)
                                .sorted(Comparator.comparing(Empleado::getSalario))
                                        .collect(toList());
        System.out.println(mayorSalario);


        System.out.println("Promedio general");
        double promedioSalario = empleados.stream()
                .collect(Collectors.averagingDouble(Empleado::getSalario));
        System.out.println(promedioSalario);

        System.out.println("Empleado por departamento y su sueldo");
        Map<String,Double> empleadoXdepto = empleados.stream()
                .collect(groupingBy(Empleado::getDepartamento,Collectors.summingDouble(Empleado::getSalario)));

        empleadoXdepto.forEach((departamento,salario) -> {
            System.out.println( departamento +" " + salario);
        });

        System.out.println("mas jovenes");
        List<Empleado> masJoven = empleados.stream()
                .sorted(Comparator.comparingInt(Empleado::getEdad))
                        .limit(2)
                                .collect(toList());

        System.out.println(masJoven);

    }

}