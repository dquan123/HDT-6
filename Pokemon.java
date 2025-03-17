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
    
    // Constructor
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
    
    // Getters
    public String getName() { return name; }
    public int getPokedexNumber() { return pokedexNumber; }
    public String getType1() { return type1; }
    public String getType2() { return type2; }
    public String getClassification() { return classification; }
    public double getHeight() { return height; }
    public double getWeight() { return weight; }
    public List<String> getAbilities() { return abilities; }
    public int getGeneration() { return generation; }
    public boolean isLegendaryStatus() { return legendaryStatus; }
    
    // Método para verificar si el Pokémon posee una habilidad dada
    public boolean hasAbility(String ability) {
        for (String a : abilities) {
            if (a.trim().equalsIgnoreCase(ability.trim())) {
                return true;
            }
        }
        return false;
    }
    
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
