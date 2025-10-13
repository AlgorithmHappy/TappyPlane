package dev.gerardomarquez.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Polygon;

import dev.gerardomarquez.dto.GenerateRocksJson;
import dev.gerardomarquez.dto.LevelMapJson;
import dev.gerardomarquez.dto.RockJson;
import dev.gerardomarquez.utils.Constants;
import dev.gerardomarquez.utils.Constants.GROUND_KIND;
import dev.gerardomarquez.utils.Constants.ROCK_KIND;

/*
 * {@inheritDoc}
 */
public class GroundAndRockMapp implements GraphicEntity{

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
     * Objeto que mapea la estructura del JSON para generar las rocas.
     */
    private GenerateRocksJson generateRocksJson;

    /*
     * Tiempo transcurrido que se resetea cada N segundos para generar las rocas
     */
    private Float time;

    /*
     * Lista de rocas que se generaran en el juego
     */
    private List<Rock> listRocks;

    /*
     * Constructor para inicializar todos los sprites de ground (techos y suelos)
     * @param graphicEntityFactory Fabrica de graficos para obtener las colisiones.
     * @param textureAtlas atlas para instanciar los sprites.
     * @param kindTopGround tipo de ground del techo.
     * @param kindDownGround tipo de ground del suelo.
     */
    public GroundAndRockMapp(
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
        this.generateRocksJson = graphicEntityFactory.getGenerateRocksJson();
        this.time = Constants.ZERO;
        this.listRocks = new ArrayList<>();
    }

    /*
    * {@inheritDoc}
    */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        for(Rock rock: this.listRocks){
            if(this.time >= rock.getSecond() ){
                rock.isVisible(Boolean.TRUE);
                rock.draw(spriteBatch);
            }
        }
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
    public void gameplay() {
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

        List<Rock> rocksToRemove = new ArrayList<>();
        for(Rock rock: this.listRocks){
            if(rock.isOutOfScreenLeft() ){
                rocksToRemove.add(rock);
            }
        }
        this.listRocks.removeAll(rocksToRemove);
    }

    /*
     * Movimiento constante a la izquierda
     */
    public void move(Float delta){
        for(int i = 0; i < this.downGround.length; i++){
            this.downGround[i].moveToLeft(delta * this.velocity);
            this.topGround[i].moveToLeft(delta * this.velocity);
        }

        for(Rock rock: this.listRocks){
            if(rock.isVisible() )
                rock.moveToLeft(delta * this.velocity);
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
        for(Rock rock: this.listRocks){
            if(rock.isVisible() )
                listTopAndDownVisibleGround.addAll(rock.getPolygons() );
        }
        return listTopAndDownVisibleGround;
    }

    /*
     * Devuelve si colisiono con el jugador o no
     * @return True si colisiono, False no.
     */
    public Boolean collision(Player player){
        for(int i = 0; i < this.downGround.length; i++){
            if( this.downGround[i].isVisible() && player.getCurrentPlane().collisionGround(this.downGround[i] ) ){
                return Boolean.TRUE;
            }
            if( this.topGround[i].isVisible() && player.getCurrentPlane().collisionGround(this.topGround[i] ) ){
                return Boolean.TRUE;
            }
        }
        for(Rock rock: this.listRocks){
            if(rock.isVisible() && player.getCurrentPlane().collisionRock(rock) ){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public void triggerRock(Float delta){
        if(this.velocity == Constants.GROUND_VELOCITY && this.listRocks.isEmpty() ){
            this.time = Constants.ZERO;
            LevelMapJson levelMapJson = this.generateRocksJson.getLevels().getEasy();
            Integer maxIndex = levelMapJson.getRockMap().size();
            Integer random = (int)(Math.random() * maxIndex);

            List<RockJson> rocksJsons = levelMapJson.getRockMap().get(random);

            for(RockJson rockJson: rocksJsons ){
                this.listRocks.add(
                    new Rock(
                        ROCK_KIND.valueOf(rockJson.getKind() ),
                        rockJson.getIsDown(),
                        rockJson.getSeconds()
                    )
                );
            }
        } else if (this.velocity > Constants.GROUND_VELOCITY + 1 && this.listRocks.isEmpty() ){ // Cambiar el +1

        } else if (this.velocity < Constants.GROUND_VELOCITY + 2 && this.listRocks.isEmpty() ){ // Cambiar el +2

        }

        if(this.time >= this.generateRocksJson.getMaxSecond() ){
            this.time = this.generateRocksJson.getMaxSecond();
        }
        this.time += delta;
    }
}
