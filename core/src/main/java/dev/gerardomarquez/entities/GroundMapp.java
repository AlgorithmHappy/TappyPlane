package dev.gerardomarquez.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Polygon;

import dev.gerardomarquez.utils.Constants;
import dev.gerardomarquez.utils.Constants.GROUND_KIND;

/*
 * {@inheritDoc}
 */
public class GroundMapp implements GraphicEntity{

    /*
    * Nombre del Ground, usado para identificar el sprite que usuara el ground.
    */
    private String name;

    /*
     * Sprite a utilizar
     */
    private Ground[] topGround;

    /*
     * Sprite a utilizar
     */
    private Ground[] downGround;

    /*
     * Velocidad de movimiento del suelo y techo
     */
    private Float velocity;

    /*
     * Constructor para inicializar todos los sprites de ground (techos y suelos)
     * @param graphicEntityFactory Fabrica de graficos para obtener las colisiones.
     * @param textureAtlas atlas para instanciar los sprites.
     * @param kindTopGround tipo de ground del techo.
     * @param kindDownGround tipo de ground del suelo.
     */
    public GroundMapp(
        GraphicEntityFactory graphicEntityFactory,
        TextureAtlas textureAtlas,
        GROUND_KIND kindTopGround,
        GROUND_KIND kindDownGround
    ){
        this.topGround = new Ground[] {
            new Ground(
                graphicEntityFactory.getShapeBySpriteName(Constants.SPRITE_NAME_GROUND + kindTopGround.getValue() ),
                textureAtlas,
                kindDownGround
            ),
            new Ground(
                graphicEntityFactory.getShapeBySpriteName(Constants.SPRITE_NAME_GROUND + kindTopGround.getValue() ),
                textureAtlas,
                kindDownGround
            ),
            new Ground(
                graphicEntityFactory.getShapeBySpriteName(Constants.SPRITE_NAME_GROUND + kindTopGround.getValue() ),
                textureAtlas,
                kindDownGround
            )
        };

        Float position = null;
        for(Ground ground: this.topGround){
            ground.topDown();
            ground.moveToNextRightGround(position);
            position = ground.getX();
        }

        this.downGround = new Ground[] {
            new Ground(
                graphicEntityFactory.getShapeBySpriteName(Constants.SPRITE_NAME_GROUND + kindTopGround.getValue() ),
                textureAtlas,
                kindDownGround
            ),
            new Ground(
                graphicEntityFactory.getShapeBySpriteName(Constants.SPRITE_NAME_GROUND + kindTopGround.getValue() ),
                textureAtlas,
                kindDownGround
            ),
            new Ground(
                graphicEntityFactory.getShapeBySpriteName(Constants.SPRITE_NAME_GROUND + kindTopGround.getValue() ),
                textureAtlas,
                kindDownGround
            )
        };

        position = null;
        for(Ground ground: this.downGround){
            ground.moveToNextRightGround(position);
            position = ground.getX();
        }

        this.name = this.topGround[Constants.FIRST_INDEX] + Constants.PIPE + this.downGround[Constants.FIRST_INDEX];
        this.velocity = Constants.GROUND_VELOCITY;
    }

    /*
    * {@inheritDoc}
    */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        this.gameplay();
        for(Ground ground: this.topGround){
            if(ground.isVisible() ){
                ground.draw(spriteBatch);
            }
        }
        for(Ground ground: this.downGround){
            if(ground.isVisible() ){
                ground.draw(spriteBatch);
            }
        }
    }

    /*
    * {@inheritDoc}
    */
    @Override
    public String getName() {
        return this.name;
    }

    /*
     * Logica que contendra el ground
     */
    private void gameplay() {
        this.move();
        for(int i = 0; i < this.topGround.length; i++){
            if(topGround[i].isOutOfScreenLeft() ){
                Float maxPositionX = Constants.ZERO;
                for(int j = 0; j < this.topGround.length; j++){
                    if(topGround[j].getX() > maxPositionX){
                        maxPositionX = topGround[j].getX();
                    }
                }
                topGround[i].moveToNextRightGround(maxPositionX);
            }
            if(downGround[i].isOutOfScreenLeft() ){
                Float maxPositionX = Constants.ZERO;
                for(int j = 0; j < this.downGround.length; j++){
                    if(downGround[j].getX() > maxPositionX){
                        maxPositionX = downGround[j].getX();
                    }
                }
                downGround[i].moveToNextRightGround(maxPositionX);
            }
        }
    }

    /*
     * Movimiento constante a la izquierda
     */
    private void move(){
        Float delta = Gdx.graphics.getDeltaTime();
        for(int i = 0; i < this.downGround.length; i++){
            this.downGround[i].moveToLeft(delta * this.velocity);
            this.topGround[i].moveToLeft(delta * this.velocity);
        }
    }

    public List<Polygon> getPolygons(){
        List<Polygon> listTopAndDownVisibleGround = new ArrayList<>();
        for(int i = 0; i < this.topGround.length; i++){
            if(topGround[i].isVisible() ){
                listTopAndDownVisibleGround.addAll(topGround[i].getPolygons() );
            }
            if(downGround[i].isVisible() ){
                listTopAndDownVisibleGround.addAll(downGround[i].getPolygons() );
            }
        }
        return listTopAndDownVisibleGround;
    }

    /*
     * Devuelve si colisiono con el jugador o no
     * @return True si colisiono, False no.
     */
    public Boolean collision(Player player){
        for(Ground ground: this.downGround){
            if(ground.isVisible() && player.getCurrentPlane().collisionGround(ground) ){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
