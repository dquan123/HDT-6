import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Clase fábrica para crear una instancia de Map que almacene objetos Pokemon.
 * Permite seleccionar entre diferentes implementaciones de Map en tiempo de ejecución.
 */
public class MapFactory {
    
    /**
     * Retorna una implementación de Map<String, Pokemon> según la opción proporcionada.
     * <ul>
     *   <li>1 - HashMap</li>
     *   <li>2 - TreeMap</li>
     *   <li>3 - LinkedHashMap</li>
     * </ul>
     *
     * @param opcion La opción de implementación a utilizar.
     * @return un objeto Map con la implementación seleccionada.
     * @throws IllegalArgumentException si la opción no es 1, 2 o 3.
     */
    public static Map<String, Pokemon> getMap(int opcion) {
        switch(opcion) {
            case 1:
                return new HashMap<>();
            case 2:
                return new TreeMap<>();
            case 3:
                return new LinkedHashMap<>();
            default:
                throw new IllegalArgumentException("Opción no válida. Debe seleccionar 1, 2 o 3.");
        }
    }
}
