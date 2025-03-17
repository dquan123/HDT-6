public class Pokemon implements Serializable {
    private String name;
    private String type1;
    private String type2;
    private String ability;
    // Otros atributos según el archivo

    public Pokemon(String name, String type1, String type2, String ability) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.ability = ability;
    }

    // Getters y setters

    @Override
    public String toString() {
        return "Pokemon{" +
               "name='" + name + '\'' +
               ", type1='" + type1 + '\'' +
               ", type2='" + type2 + '\'' +
               ", ability='" + ability + '\'' +
               '}';
    }

    // equals y hashCode si se requiere para las operaciones en colecciones
}
