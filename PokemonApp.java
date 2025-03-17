import java.io.*;
import java.util.*;

/**
 * Clase principal que administra la aplicación de Pokémon.
 * Permite cargar datos desde un archivo CSV, agregar Pokémon a una colección personalizada,
 * mostrar información de Pokémon, listar Pokémon ordenados por su tipo primario, medir el tiempo
 * de la operación de ordenación y buscar Pokémon por habilidad.
 */
public class PokemonApp {
    // Colección completa de Pokémon (clave: nombre)
    private Map<String, Pokemon> pokemonMap;
    // Colección personalizada del usuario (se utiliza LinkedHashMap para mantener el orden de inserción)
    private Map<String, Pokemon> userCollection;
    
    /**
     * Construye una nueva instancia de PokemonApp utilizando la implementación de Map especificada.
     *
     * @param mapOption La opción de implementación a utilizar (1: HashMap, 2: TreeMap, 3: LinkedHashMap).
     */
    public PokemonApp(int mapOption) {
        // Se selecciona la implementación del Map para la colección completa usando el patrón Factory
        pokemonMap = MapFactory.getMap(mapOption);
        // La colección del usuario se guarda en un LinkedHashMap para preservar el orden de inserción
        userCollection = new LinkedHashMap<>();
    }
    
    /**
     * Carga los datos desde un archivo CSV. Se asume que el archivo tiene la siguiente estructura:
     * Nombre,Pokedex number,Type1,Type2,Classification,Height(m),Weight(kg),Abilities,Generation,Legendary Status.
     * 
     * Se utiliza una expresión regular para separar correctamente las comas que no están dentro de comillas.
     *
     * @param filePath La ruta completa del archivo CSV.
     */
    public void loadData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Leer la línea de encabezado
            String header = br.readLine();
            if (header == null) {
                System.out.println("El archivo está vacío.");
                return;
            }
            
            // Leer cada línea del archivo
            while ((line = br.readLine()) != null) {
                // Utiliza regex para separar comas que no estén dentro de comillas
                String[] datos = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                
                if (datos.length < 10) {
                    System.out.println("Línea inválida (menos de 10 campos): " + line);
                    continue;
                }
                
                // Limpiar posibles comillas en cada campo
                for (int i = 0; i < datos.length; i++) {
                    datos[i] = datos[i].trim().replaceAll("^\"|\"$", "");
                }
                
                try {
                    String name = datos[0];
                    int pokedexNumber = Integer.parseInt(datos[1]);
                    String type1 = datos[2];
                    String type2 = datos[3];
                    String classification = datos[4];
                    double height = Double.parseDouble(datos[5]);
                    double weight = Double.parseDouble(datos[6]);
                    String abilitiesStr = datos[7];
                    int generation = Integer.parseInt(datos[8]);
                    // Se asume que "Legendary Status" es "True" o "False"
                    boolean legendaryStatus = Boolean.parseBoolean(datos[9]);
                    
                    Pokemon p = new Pokemon(name, pokedexNumber, type1, type2, classification, height, weight, abilitiesStr, generation, legendaryStatus);
                    pokemonMap.put(name, p);
                } catch (NumberFormatException e) {
                    System.out.println("Error al parsear la línea: " + line);
                    System.out.println("Detalle: " + e.getMessage());
                }
            }
            System.out.println("Datos cargados exitosamente. Total de Pokémon: " + pokemonMap.size());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    
    /**
     * Agrega un Pokémon a la colección personalizada del usuario, si existe en la colección completa.
     *
     * @param name El nombre del Pokémon a agregar.
     */
    public void agregarPokemonAUsuario(String name) {
        if (userCollection.containsKey(name)) {
            System.out.println("El Pokémon ya se encuentra en la colección del usuario.");
        } else if (pokemonMap.containsKey(name)) {
            userCollection.put(name, pokemonMap.get(name));
            System.out.println("Pokémon agregado a la colección del usuario.");
        } else {
            System.out.println("Error: Pokémon no encontrado en los datos.");
        }
    }

        /**
     * Retorna la colección completa de Pokémon.
     *
     * @return el mapa de Pokémon.
     */
    public Map<String, Pokemon> getPokemonMap() {
        return pokemonMap;
    }

    /**
     * Retorna la colección personalizada del usuario.
     *
     * @return el mapa de la colección del usuario.
     */
    public Map<String, Pokemon> getUserCollection() {
        return userCollection;
    }

    
    /**
     * Muestra los datos completos de un Pokémon buscado por nombre.
     *
     * @param name El nombre del Pokémon.
     */
    public void mostrarDatosPokemon(String name) {
        Pokemon p = pokemonMap.get(name);
        if (p != null) {
            System.out.println(p);
        } else {
            System.out.println("Error: Pokémon no encontrado.");
        }
    }
    
    /**
     * Muestra el nombre y el tipo primario (Type1) de los Pokémon en la colección personalizada del usuario,
     * ordenados por su tipo primario.
     */
    public void mostrarColeccionUsuarioOrdenadaPorTipo1() {
        if (userCollection.isEmpty()) {
            System.out.println("La colección del usuario está vacía.");
            return;
        }
        List<Pokemon> lista = new ArrayList<>(userCollection.values());
        lista.sort(Comparator.comparing(Pokemon::getType1));
        System.out.println("Pokémon en la colección del usuario (ordenados por Type1):");
        for (Pokemon p : lista) {
            System.out.println("Nombre: " + p.getName() + " - Type1: " + p.getType1());
        }
    }
    
    /**
     * Muestra el nombre y el tipo primario (Type1) de todos los Pokémon (de la colección completa)
     * ordenados por su tipo primario. Además, mide y muestra el tiempo de ejecución de la operación.
     */
    public void mostrarTodosPokemonsOrdenadosPorTipo1() {
        if (pokemonMap.isEmpty()) {
            System.out.println("No hay datos de Pokémon cargados.");
            return;
        }
        
        // Medir el tiempo de ejecución en nanosegundos
        long startTime = System.nanoTime();
        
        List<Pokemon> lista = new ArrayList<>(pokemonMap.values());
        lista.sort(Comparator.comparing(Pokemon::getType1));
        
        System.out.println("Todos los Pokémon (ordenados por Type1):");
        for (Pokemon p : lista) {
            System.out.println("Nombre: " + p.getName() + " - Type1: " + p.getType1());
        }
        
        long endTime = System.nanoTime();
        long tiempo = endTime - startTime;
        System.out.println("Tiempo de ejecución: " + (tiempo / 1_000_000.0) + " ms");
    }
    
    /**
     * Muestra los nombres de los Pokémon que poseen la habilidad indicada.
     * Se busca la habilidad en cada una de las habilidades del Pokémon sin distinguir mayúsculas o minúsculas.
     *
     * @param habilidad La habilidad a buscar.
     */
    public void mostrarPokemonsPorHabilidad(String habilidad) {
        boolean encontrado = false;
        for (Pokemon p : pokemonMap.values()) {
            for (String abil : p.getAbilities()) {
                if (abil.toLowerCase().contains(habilidad.toLowerCase())) {
                    System.out.println("Nombre: " + p.getName() + " - Habilidad: " + abil);
                    encontrado = true;
                    break;
                }
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron Pokémon con la habilidad: " + habilidad);
        }
    }
    
    /**
     * Inicia el menú interactivo que permite al usuario ejecutar las diferentes operaciones.
     *
     * @param sc Un objeto Scanner para la entrada de datos.
     */
    public void iniciarMenu(Scanner sc) {
        int opcion = 0;
        while (opcion != 7) {
            System.out.println("\nMenú de Opciones:");
            System.out.println("1. Agregar Pokémon a la colección del usuario");
            System.out.println("2. Mostrar los datos de un Pokémon");
            System.out.println("3. Mostrar la colección del usuario (nombre y Type1) ordenados por Type1");
            System.out.println("4. Mostrar todos los Pokémon (nombre y Type1) ordenados por Type1");
            System.out.println("5. Mostrar Pokémon por habilidad");
            System.out.println("7. Salir");
            System.out.print("Ingrese una opción: ");
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Ingrese un número.");
                continue;
            }
            
            switch(opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del Pokémon a agregar: ");
                    String nameAgregar = sc.nextLine();
                    if (!nameAgregar.isEmpty()) {
                        // Forzar formato: primera letra mayúscula y el resto en minúsculas
                        nameAgregar = nameAgregar.substring(0, 1).toUpperCase() + nameAgregar.substring(1).toLowerCase();
                    }
                    agregarPokemonAUsuario(nameAgregar);
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del Pokémon para mostrar sus datos: ");
                    String nameMostrar = sc.nextLine();
                    if (!nameMostrar.isEmpty()) {
                        nameMostrar = nameMostrar.substring(0, 1).toUpperCase() + nameMostrar.substring(1).toLowerCase();
                    }
                    mostrarDatosPokemon(nameMostrar);
                    break;
                case 3:
                    mostrarColeccionUsuarioOrdenadaPorTipo1();
                    break;
                case 4:
                    mostrarTodosPokemonsOrdenadosPorTipo1();
                    break;
                case 5:
                    System.out.print("Ingrese la habilidad a buscar: ");
                    String habilidad = sc.nextLine().trim();
                    mostrarPokemonsPorHabilidad(habilidad);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opción no reconocida.");
            }
        }
    }
    
    /**
     * Método principal que inicia la aplicación.
     * Permite seleccionar la implementación de Map a usar, carga los datos desde el CSV y
     * ejecuta el menú interactivo.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Seleccione la implementación de Map a usar:");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");
        int opcionMap;
        try {
            opcionMap = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opción inválida. Se selecciona por defecto HashMap.");
            opcionMap = 1;
        }
        PokemonApp app = new PokemonApp(opcionMap);
        
        // Definir la ruta completa del archivo CSV (ajustar según la ubicación)
        String filePath = "C:\\Users\\dquan\\OneDrive\\Documentos\\Diego Quan\\UVG\\Ciclo 3\\Algoritmos y Estructura de datos\\Hoja de trabajo 6\\HDT-6\\pokemon_data_pokeapi.csv";
        app.loadData(filePath);
        
        // Iniciar el menú interactivo pasando el Scanner
        app.iniciarMenu(sc);
        
        sc.close();
    }
}
