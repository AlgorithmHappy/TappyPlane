package dev.gerardomarquez.dto;

/*
 * Clase que mapea la estructura del JSON para los niveles.
 */
public class LevelsJson {
    
    /*
     * Nivel facil
     */
    private LevelMapJson easy;

    /*
     * Nivel medio
     */
    private LevelMapJson medium;

    /*
     * Nivel dificil
     */
    private LevelMapJson hard;

    /*
     * Getter para obtener el nivel facil
     * @return easy Nivel facil
     */
    public LevelMapJson getEasy() {
        return easy;
    }

    /*
     * Setter para setear el nivel facil
     * @param easy Nivel facil
     */
    public void setEasy(LevelMapJson easy) {
        this.easy = easy;
    }

    /*
     * Getter para obtener el nivel medio
     * @return medium Nivel medio
     */
    public LevelMapJson getMedium() {
        return medium;
    }

    /*
     * Setter para setear el nivel medio
     * @param medium Nivel medio
     */
    public void setMedium(LevelMapJson medium) {
        this.medium = medium;
    }

    /*
     * Getter para obtener el nivel dificil
     * @return hard Nivel dificil
     */
    public LevelMapJson getHard() {
        return hard;
    }

    /*
     * Setter para setear el nivel dificil
     * @param hard Nivel dificil
     */
    public void setHard(LevelMapJson hard) {
        this.hard = hard;
    }
}
