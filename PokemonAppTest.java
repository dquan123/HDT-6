import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Clase de pruebas unitarias para la clase PokemonApp.
 */
public class PokemonAppTest {
    
    private PokemonApp app;
    
    /**
     * Configura el entorno de pruebas antes de cada test.
     * Se crea una instancia de PokemonApp y se agregan dos Pokémon de ejemplo.
     */
    @BeforeEach
    public void setUp() {
        // Se utiliza la implementación HashMap (opción 1)
        app = new PokemonApp(1);
        // Crear Pokémon de ejemplo
        Pokemon charmander = new Pokemon("Charmander", 4, "Fire", "", "Lizard", 0.6, 8.5, "Blaze;Solar Power", 1, false);
        Pokemon squirtle = new Pokemon("Squirtle", 7, "Water", "", "Tiny Turtle", 0.5, 9.0, "Torrent;Rain Dish", 1, false);
        // Agregar los Pokémon a la colección completa usando los getters
        app.getPokemonMap().put(charmander.getName(), charmander);
        app.getPokemonMap().put(squirtle.getName(), squirtle);
    }
    
    /**
     * Prueba la operación de agregar un Pokémon a la colección del usuario.
     * Verifica que el Pokémon se agregue y que no se duplique si se intenta agregar de nuevo.
     */
    @Test
    public void testAgregarPokemonAUsuario() {
        // La colección del usuario debe estar inicialmente vacía.
        assertTrue(app.getUserCollection().isEmpty());
        
        // Agregar "Charmander" a la colección del usuario.
        app.agregarPokemonAUsuario("Charmander");
        // Se espera que la colección del usuario contenga a "Charmander".
        assertTrue(app.getUserCollection().containsKey("Charmander"));
        
        // Intentar agregar nuevamente "Charmander" no debe incrementar el tamaño de la colección.
        int sizeAntes = app.getUserCollection().size();
        app.agregarPokemonAUsuario("Charmander");
        assertEquals(sizeAntes, app.getUserCollection().size());
    }
    
    /**
     * Prueba la operación de mostrar los datos de un Pokémon.
     * Se captura la salida de la consola para verificar que se muestre la información correcta.
     */
    @Test
    public void testMostrarDatosPokemon() {
        // Redirigir la salida estándar para capturarla.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        // Invocar la operación para mostrar los datos de "Squirtle".
        app.mostrarDatosPokemon("Squirtle");
        
        // Restaurar la salida estándar.
        System.setOut(originalOut);
        String salida = outContent.toString();
        
        // Verificar que la salida contenga el nombre "Squirtle" y alguna de sus habilidades.
        assertTrue(salida.contains("Squirtle"));
        assertTrue(salida.contains("Torrent") || salida.contains("Rain Dish"));
    }
    
    /**
     * Prueba la operación de buscar Pokémon por habilidad.
     * Se captura la salida de la consola para verificar que se encuentre el Pokémon que posee la habilidad.
     */
    @Test
    public void testMostrarPokemonsPorHabilidad() {
        // Redirigir la salida estándar para capturarla.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        // Buscar por la habilidad "Blaze" (se espera encontrar a Charmander).
        app.mostrarPokemonsPorHabilidad("Blaze");
        
        // Restaurar la salida estándar.
        System.setOut(originalOut);
        String salida = outContent.toString();
        
        // Verificar que la salida contenga "Charmander" y "Blaze".
        assertTrue(salida.contains("Charmander"));
        assertTrue(salida.contains("Blaze"));
    }
}
