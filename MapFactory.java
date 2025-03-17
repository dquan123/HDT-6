import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapFactory {
    /**
     * Retorna una implementación de Map<String, Pokemon> según la opción:
     * 1 -> HashMap
     * 2 -> TreeMap
     * 3 -> LinkedHashMap
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
