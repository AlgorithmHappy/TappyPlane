package dev.gerardomarquez.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Shape2D;

import dev.gerardomarquez.utils.Constants;
import dev.gerardomarquez.utils.Constants.PLANE_COLOR;

/*
 * {@inheritDoc}
 */
public class Plane implements GraphicEntity {

    /*
     * Nombre del sprite
     */
    private String name;

    /*
     * Sprite que se va a pintar en el juego
     */
    private Sprite sprite;

    /*
     * Colliosion del sprite
     */
    private List<Polygon> polygonCollision;

    /*
     * Constructor que define el color y el frame del avion
     * @param atlas TextureAtlas para crear el sprite porque el nombre del atlas del plane
     * debe ser una combinacion de parametros para construir el nombre por completo
     */
    public Plane(TextureAtlas atlas, PLANE_COLOR color, Integer number) {
        this.name = Constants.SPRITE_NAME_PLANE +
            color.getValue() +
            number.toString();

        this.sprite = atlas.createSprite(name);
        this.sprite.setOrigin(this.sprite.getWidth() / Constants.HALF, this.sprite.getHeight() / Constants.HALF);
        this.sprite.setPosition(Constants.VIRTUAL_WIDTH / Constants.ONE_SIXTH, Constants.VIRTUAL_HEIGHT / Constants.HALF);
        this.polygonCollision = new ArrayList<>();
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        sprite.draw(spriteBatch);
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /*
     * Aplicar la gravedad cada tiempo "delta"
     * @param y Posicion en el eje de la y al que se tendria que mover abajo cada tiempo "delta"
     * @param rotation Rotacion que tendria que tener el avion para que haga el efecto de caer en picada
     */
    public void applyGravity(Float y, Float rotation){
        Float currentPosition = this.sprite.getY();
        this.sprite.setY(currentPosition + y);
        this.sprite.setRotation(rotation);
        this.polygonCollision.forEach(
            it -> {
                it.setPosition(it.getX(), it.getY() + y);
                it.setRotation(this.sprite.getRotation() );
            }
        );
    }

    /*
     * Metodo para setear la figura de colision que contendra el sprite
     * @param collisionShape Figura de collision
     */
    public void setCollisionShape(List<Shape2D> collisionShape){
        for(Shape2D shape: collisionShape){
            Polygon polygon = (Polygon) shape;
            polygon.setOrigin(
                polygon.getBoundingRectangle().getWidth() / Constants.HALF, polygon.getBoundingRectangle().getHeight() / Constants.HALF
            );
            polygon.setPosition(
                (Constants.VIRTUAL_WIDTH / Constants.ONE_SIXTH) + Constants.PLANE_OFFSET_X, (Constants.VIRTUAL_HEIGHT / Constants.HALF) + Constants.PLANE_OFFSET_Y
            );
            polygon.setScale(this.sprite.getScaleX(), this.sprite.getScaleY() );
            this.polygonCollision.add(polygon);
        }
    }

    /*
     * Obtiene los poligonos de las colisiones
     * @return Lista de poligonos que hace la figura del sprite
     */
    public List<Polygon> getPoliPolygons(){
        return this.polygonCollision;
    }

    /*
     * Metodo que indica si colisiono con el suelo o el techo
     * @param ground Suelo o techo
     */
    public Boolean collisionGround(Ground ground){
        Boolean intersect = Boolean.FALSE;
        for(Polygon polygonPlane: this.polygonCollision){
            for(Polygon polygonGround: ground.getPolygons() ){
                if(Intersector.overlapConvexPolygons(polygonPlane, polygonGround ) ){
                    return Boolean.TRUE;
                }
            }
        }
        return intersect;
    }

    /*
     * Metodo que indica si colisiono con el suelo o el techo
     * @param ground Suelo o techo
     */
    public Boolean collisionRock(Rock rock){
        Boolean intersect = Boolean.FALSE;
        for(Polygon polygonPlane: this.polygonCollision){
            for(Polygon polygonRock: rock.getPolygons() ){
                if(Intersector.overlapConvexPolygons(polygonPlane, polygonRock) ){
                    return Boolean.TRUE;
                }
            }
        }
        return intersect;
    }
}
