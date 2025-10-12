package dev.gerardomarquez.dto;

import java.util.List;

/*
 * Clase que mapea la estructura del JSON para el mapa del nivel.
 */
public class LevelMapJson {

    /*
     * Lista de listas de rocas a generar en el nivel cada 10 segundos.
     */
    private List<List<RockJson> > rockMap;

    /*
     * Getter para obtener la lista de listas de rocas a generar en el nivel cada 10 segundos.
     * @return rockMap Lista de listas de rocas a generar en el nivel cada 10 segundos.
     */
    public List<List<RockJson>> getRockMap() {
        return rockMap;
    }

    /*
     * Setter para setear la lista de listas de rocas a generar en el nivel cada 10 segundos.
     * @param rockMap Lista de listas de rocas a generar en el nivel cada 10 segundos.
     */
    public void setRockMap(List<List<RockJson>> rockMap) {
        this.rockMap = rockMap;
    }
}
