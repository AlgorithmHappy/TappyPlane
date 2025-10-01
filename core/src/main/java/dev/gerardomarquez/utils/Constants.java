package dev.gerardomarquez.utils;

/*
 * Clase que contiene constantes usadas en el juego.
 */
public class Constants {
    public static final String PATH_ATLAS = "sheet.atlas";

    public static final Integer VIRTUAL_WIDTH = 1280;
    public static final Integer VIRTUAL_HEIGHT = 720;

    /*
     * Nombres de sprites en el atlas.
     */
    public static final String SPRITE_NAME_BACKGROUND = "background";
    public static final String SPRITE_NAME_PLANE = "plane";

    /*
     * Colores del avion
     */
    public enum PLANE_COLOR {
        BLUE("Blue"),
        RED("Red"),
        GREEN("Green"),
        YELLOW("Yellow");

        /*
         * Color del avion
         */
        private final String value;

        /*
         * Constructor que setea el color del avion con el enum
         * @param value Color del avion
         */
        PLANE_COLOR(String value) {
            this.value = value;
        }

        /*
         * Getter para obtener el color del avion
         * @return value Devuelve el color al que esta ligado en el atlas
         */
        public String getValue() {
            return value;
        }
    }

    /*
     * Fotogramas del avion
     */
    public enum PLANE_FRAMES {
        FIRST_FRAME(1),
        SECOND_FRAME(2),
        THIRD_FRAME(3);

        /*
         * Numero de fotograma
         */
        private final int number;

        /*
         * Constructor que setea el numero de fotograma
         * @param number Numero de fotograma de la animacion del avion en el atlas
         */
        PLANE_FRAMES(Integer number) {
            this.number = number;
        }

        /*
         * Devuelve el numero de fotograma del atlas
         * @return Numero de fotograma de la animacion del avion
         */
        public Integer getNumero() {
            return number;
        }
    }

    /*
     * Variables globales
     */
    public static final Integer FIRST_INDEX = 0;
    public static final Integer SECOND_INDEX = 1;

}
