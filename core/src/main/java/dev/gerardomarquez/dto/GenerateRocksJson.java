package dev.gerardomarquez.dto;

/*
 * Clase que mapea la estructura del JSON para generar las rocas.
 */
public class GenerateRocksJson {
    /*
     * Segundo minimo en el que se puede generar la roca
     */
    private Float minSecond;

    /*
     * Segundo maximo en el que se puede generar la roca
     */
    private Float maxSecond;

    /*
     * Niveles del juego para que se identifique a que velocidad se deberian generar las rocas
     */
    private LevelsJson levels;

    /*
     * Getter para obtener el segundo minimo en el que se puede generar la roca
     * @return Segundo minimo en el que se puede generar la roca
     */
    public Float getMinSecond() {
        return minSecond;
    }

    /*
     * Setter para setear el segundo minimo en el que se puede generar la roca
     * @param minSecond Segundo minimo en el que se puede generar la roca
     */
    public void setMinSecond(Float minSecond) {
        this.minSecond = minSecond;
    }

    /*
     * Getter para obtener el segundo maximo en el que se puede generar la roca
     * @return Segundo maximo en el que se puede generar la roca
     */
    public Float getMaxSecond() {
        return maxSecond;
    }

    /*
     * Setter para setear el segundo maximo en el que se puede generar la roca
     * @param maxSecond Segundo maximo en el que se puede generar la roca
     */
    public void setMaxSecond(Float maxSecond) {
        this.maxSecond = maxSecond;
    }

    /*
     * Getter para obtener los niveles del juego
     * @return Niveles del juego
     */
    public LevelsJson getLevels() {
        return levels;
    }

    /*
     * Setter para setear los niveles del juego
     * @param levels Niveles del juego
     */
    public void setLevels(LevelsJson levels) {
        this.levels = levels;
    }
}
