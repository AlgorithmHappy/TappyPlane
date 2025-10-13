package dev.gerardomarquez.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Shape2D;

import dev.gerardomarquez.utils.Constants;
import dev.gerardomarquez.utils.Constants.ROCK_KIND;

/*
 * {@inheritDoc}
 */
public class Rock implements GraphicEntity{

    /*
     * Nombre de la roca, usado para identificar el sprite que usuara la roca.
     */
    private String name;

    /*
     * Sprite a utilizar para la roca
     */
    private Sprite sprite;

    /*
    * Colliosion del sprite
    */
    private List<Polygon> listPolygonCollisions;

    /*
     * Indica si la roca esta visible o no
     */
    private Boolean isVisible;

    /*
     * Segundo en el que se generara la roca
     */
    private Float second;

    /*
     * Constructor para inicializar el sprite
     * @param rockKind Tipo de roca para inicializar el sprite a utilizar
     * @param isDownRock Indica si la roca es de techo o suelo
     * @param second Segundo en el que se generara la roca
     */
    public Rock(ROCK_KIND rockKind, Boolean isDownRock, Float second){
        if(rockKind.getValue().equals(ROCK_KIND.RANDOM.getValue() ) ){
            int random = (int)(Math.random() * 3);
            switch(random){
                case 0:
                    rockKind = ROCK_KIND.GRASS;
                    break;
                case 1:
                    rockKind = ROCK_KIND.ICE;
                    break;
                case 2:
                    rockKind = ROCK_KIND.SNOW;
                    break;
            }
        }
        this.name = Constants.SPRITE_NAME_ROCK + rockKind.getValue() + (isDownRock ? Constants.DOWN : new String() );
        this.sprite = GraphicEntityFactory.getInstance().getTextureAtlas().createSprite(this.name);
        this.sprite.setX(Constants.VIRTUAL_WIDTH);
        this.sprite.setY(isDownRock ? Constants.VIRTUAL_HEIGHT - this.sprite.getHeight() : this.sprite.getY() );
        this.listPolygonCollisions = new ArrayList<>();
        for(Shape2D shape2d: GraphicEntityFactory.getInstance().getShapeBySpriteName(this.name) ){
            Polygon polygon =(Polygon)shape2d;
            polygon.setPosition(this.sprite.getX() + Constants.ROCK_OFFSET_X, this.sprite.getY() + Constants.ROCK_OFFSET_Y);
            this.listPolygonCollisions.add(polygon);
        }
        this.isVisible = Boolean.FALSE;
        this.second = second;
    }

    /*
    * {@inheritDoc}
    */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        if(this.isVisible == Boolean.TRUE)
            this.sprite.draw(spriteBatch);
    }

    /*
    * {@inheritDoc}
    */
    @Override
    public String getName() {
        return this.name;
    }

    /*
     * Movimiento constante a la izquierda
     * @param add Valor a restar en X para mover el sprite a la izquierda
     */
    public void moveToLeft(Float add){
        this.sprite.setX(this.sprite.getX() - add);
        for(Polygon polygon: this.listPolygonCollisions){
            polygon.setPosition(this.sprite.getX() + Constants.ROCK_OFFSET_X, this.sprite.getY() + Constants.ROCK_OFFSET_Y);
        }
    }

    /*
     * Metodo que indica si la roca salio del lado izquierdo de la pantalla
     * @return boleano que indica con true que si salio
     */
    public Boolean isOutOfScreenLeft() {
        if( (this.sprite.getX() + this.sprite.getWidth() ) < Constants.ZERO){
            this.isVisible = Boolean.FALSE;
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /*
     * Setter para cambiar la visibilidad de la roca
     * @param isVisible Boleano que indica si la roca es visible o no
     */
    public void isVisible(Boolean isVisible){
        this.isVisible = isVisible;
    }

    /*
     * Getter para obtener si la roca es visible o no
     * @return isVisible Devuelve si la roca es visible o no
     */
    public Boolean isVisible(){
        return this.isVisible;
    }

    /*
     * Getter para obtener el segundo en el que se generara la roca
     * @return second Devuelve el segundo en el que se generara la roca
     */
    public Float getSecond() {
        return this.second;
    }

    /*
     * Obtiene los poligonos de las colisiones
     * @return Lista de poligonos que hace la figura del sprite
     */
    public List<Polygon> getPolygons(){
        return this.listPolygonCollisions;
    }
}
