/**
 * Representa un Pokémon con sus atributos, tales como nombre, número de Pokédex,
 * tipo primario, tipo secundario, clasificación, altura, peso, habilidades, generación
 * y estado legendario.
 * 
 * Se implementa la interfaz Serializable para permitir la serialización del objeto.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pokemon implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int pokedexNumber;
    private String type1;
    private String type2;
    private String classification;
    private double height;
    private double weight;
    private List<String> abilities;
    private int generation;
    private boolean legendaryStatus;
    
    /**
     * Construye un nuevo objeto Pokemon con los atributos especificados.
     *
     * @param name            El nombre del Pokémon.
     * @param pokedexNumber   El número de Pokédex del Pokémon.
     * @param type1           El tipo primario del Pokémon.
     * @param type2           El tipo secundario del Pokémon.
     * @param classification  La clasificación del Pokémon.
     * @param height          La altura (en metros) del Pokémon.
     * @param weight          El peso (en kilogramos) del Pokémon.
     * @param abilitiesStr    Una cadena con las habilidades separadas por punto y coma.
     * @param generation      La generación en la que el Pokémon fue introducido.
     * @param legendaryStatus Verdadero si el Pokémon es legendario, falso en caso contrario.
     */
    public Pokemon(String name, int pokedexNumber, String type1, String type2,
                   String classification, double height, double weight,
                   String abilitiesStr, int generation, boolean legendaryStatus) {
        this.name = name;
        this.pokedexNumber = pokedexNumber;
        this.type1 = type1;
        this.type2 = type2;
        this.classification = classification;
        this.height = height;
        this.weight = weight;
        // Se asume que las habilidades están separadas por punto y coma (";")
        this.abilities = new ArrayList<>(Arrays.asList(abilitiesStr.split(";")));
        this.generation = generation;
        this.legendaryStatus = legendaryStatus;
    }
    
    /**
     * Retorna el nombre del Pokémon.
     * 
     * @return el nombre.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Retorna el número de Pokédex del Pokémon.
     * 
     * @return el número de Pokédex.
     */
    public int getPokedexNumber() {
        return pokedexNumber;
    }
    
    /**
     * Retorna el tipo primario del Pokémon.
     * 
     * @return el tipo primario.
     */
    public String getType1() {
        return type1;
    }
    
    /**
     * Retorna el tipo secundario del Pokémon.
     * 
     * @return el tipo secundario.
     */
    public String getType2() {
        return type2;
    }
    
    /**
     * Retorna la clasificación del Pokémon.
     * 
     * @return la clasificación.
     */
    public String getClassification() {
        return classification;
    }
    
    /**
     * Retorna la altura del Pokémon.
     * 
     * @return la altura en metros.
     */
    public double getHeight() {
        return height;
    }
    
    /**
     * Retorna el peso del Pokémon.
     * 
     * @return el peso en kilogramos.
     */
    public double getWeight() {
        return weight;
    }
    
    /**
     * Retorna la lista de habilidades del Pokémon.
     * 
     * @return una lista de habilidades.
     */
    public List<String> getAbilities() {
        return abilities;
    }
    
    /**
     * Retorna la generación en la que el Pokémon fue introducido.
     * 
     * @return la generación.
     */
    public int getGeneration() {
        return generation;
    }
    
    /**
     * Retorna el estado legendario del Pokémon.
     * 
     * @return verdadero si es legendario, falso en caso contrario.
     */
    public boolean isLegendaryStatus() {
        return legendaryStatus;
    }
    
    /**
     * Verifica si el Pokémon posee la habilidad especificada.
     *
     * @param ability La habilidad a buscar.
     * @return verdadero si el Pokémon tiene la habilidad, falso en caso contrario.
     */
    public boolean hasAbility(String ability) {
        for (String a : abilities) {
            if (a.trim().equalsIgnoreCase(ability.trim())) {
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * Retorna una representación en forma de cadena del Pokémon con todos sus atributos.
     *
     * @return una cadena que representa el Pokémon.
     */
    @Override
    public String toString() {
        return "Pokemon{" +
               "Name='" + name + '\'' +
               ", PokedexNumber=" + pokedexNumber +
               ", Type1='" + type1 + '\'' +
               ", Type2='" + type2 + '\'' +
               ", Classification='" + classification + '\'' +
               ", Height(m)=" + height +
               ", Weight(kg)=" + weight +
               ", Abilities=" + abilities +
               ", Generation=" + generation +
               ", LegendaryStatus=" + legendaryStatus +
               '}';
    }
}
