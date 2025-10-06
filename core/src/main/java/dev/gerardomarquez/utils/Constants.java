package dev.gerardomarquez.utils;

/*
 * Clase que contiene constantes usadas en el juego.
 */
public class Constants {
    public static final String PATH_ATLAS = "sheet.atlas";
    public static final String PATH_COLLISIONS = "collisions.xml";

    /*
     * Game World vars
     */
    public static final Integer VIRTUAL_WIDTH = 1280;
    public static final Integer VIRTUAL_HEIGHT = 720;
    public static final Float GRAVITY = -45f;
    public static final Float PLANE_GRADE_ROTATION = 2f;
    public static final Float PLANE_LIFT_POWER = 180f;
    public static final Float PLANE_MAX_LIFT_SPEED = 40f;
    public static final Float PLANE_MIN_ROTATION = -45f;
    public static final Float PLANE_MAX_ROTATION = 45f;
    public static final Float PLANE_FRAME_RATE_ANIMATION_PLAYER = 0.085f;
    public static final Float PLANE_OFFSET_Y = 6f;
    public static final Float PLANE_OFFSET_X = 6f;

    public static final Float GROUND_VELOCITY = 200f;

    /*
     * Nombres de sprites en el atlas.
     */
    public static final String SPRITE_NAME_BACKGROUND = "background";
    public static final String SPRITE_NAME_PLANE = "plane";
    public static final String SPRITE_NAME_GROUND = "ground";

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
        public Integer getNumber() {
            return number;
        }
    }

     /*
     * Tipos de piso y techo
     */
    public enum GROUND_KIND {
        DIRT("Dirt"),
        GRASS("Grass"),
        ICE("Ice"),
        ROCK("Rock"),
        SNOW("Snow");

        /*
         * Tipo de piso y techo
         */
        private final String value;

        /*
         * Constructor que setea el tipo del techo y piso con el enum
         * @param value Tipo del techo y piso en string
         */
        GROUND_KIND(String value) {
            this.value = value;
        }

        /*
         * Getter para obtener el tipo de techo y piso
         * @return value Devuelve el tipo de techo y piso
         */
        public String getValue() {
            return value;
        }
    }

    /*
     * Variables globales
     */
    public static final Integer FIRST_INDEX = 0;
    public static final Integer SECOND_INDEX = 1;
    public static final String PIPE = "|";
    public static final Float ZERO = 0f;
    public static final Integer HALF = 2;
    public static final Integer ONE_SIXTH = 6;

    /*
     * Collisions XML
     */
    public static final String COLLISION_TAG_BODY = "body";
    public static final String COLLISION_ATRIBUTE_NAME = "name";
    public static final String COLLISION_TAG_POLYGON = "polygon";
    public static final String COLLISION_TAG_CIRCLE = "circle";
    public static final String COLLISION_POINTS_SEPARATION = ",";
    public static final String COLLISION_CIRCLE_RADIO = "r";
    public static final String COLLISION_CIRCLE_X = "x";
    public static final String COLLISION_CIRCLE_Y = "y";

}
