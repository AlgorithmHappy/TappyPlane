package dev.gerardomarquez.dto;

/*
 * Clase que mapea la estructura del JSON para una roca.
 */
public class RockJson {

    /*
     * Segundos en los que aparecera la roca
     */
    private Float seconds;

    /*
     * Tipo de roca
     */
    private String kind;

    /*
     * Indica si la roca es de abajo o de arriba
     */
    private Boolean isDown;

    /*
     * Getter para obtener los segundos en los que aparecera la roca
     * @return Segundos en los que aparecera la roca
     */
    public Float getSeconds() {
        return seconds;
    }

    /*
     * Setter para setear los segundos en los que aparecera la roca
     * @param seconds Segundos en los que aparecera la roca
     */
    public void setSeconds(Float seconds) {
        this.seconds = seconds;
    }

    /*
     * Getter para obtener el tipo de roca
     * @return Tipo de roca
     */
    public String getKind() {
        return kind;
    }

    /*
     * Setter para setear el tipo de roca
     * @param kind Tipo de roca
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /*
     * Getter para obtener si la roca es de abajo o de arriba
     * @return isDown Devuelve true si la roca es de abajo, false si es de arriba
     */
    public Boolean getIsDown() {
        return isDown;
    }

    /*
     * Setter para setear si la roca es de abajo o de arriba
     * @param isDown True si la roca es de abajo, false si es de arriba
     */
    public void setIsDown(Boolean isDown) {
        this.isDown = isDown;
    }
}
