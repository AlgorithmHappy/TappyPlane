package dev.gerardomarquez.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import dev.gerardomarquez.utils.Constants;

/*
 * Fábrica para crear instancias de GraphicEntity que contendra los sprites, este sera
 * un singleton.
 */
public class GraphicEntityFactory {

    /*
     * Instancia singleton de la fábrica.
     */
    private static GraphicEntityFactory instance = null;

    /*
     * SpriteBatch que se usará para dibujar los sprites.
     */
    private SpriteBatch spriteBatch;

    /*
     * Atlas de texturas que contendrá los sprites.
     */
    private TextureAtlas atlas;

    /*
     * Array que contendrá las entidades gráficas creadas.
     */
    private Array<GraphicEntity> entities;

    /*
     * Constructor privado para evitar instanciación externa.
     */
    private GraphicEntityFactory(){
        this.spriteBatch = new SpriteBatch();
        this.atlas = new TextureAtlas(Gdx.files.internal(Constants.PATH_ATLAS) );
        this.entities = new Array<GraphicEntity>();
    }

    /*
     * Obtiene la instancia singleton de la fábrica.
     * @return La instancia singleton de la fábrica.
     */
    public static GraphicEntityFactory getInstance() {
        if(instance == null){
            instance = new GraphicEntityFactory();
        }
        return instance;
    }

    /*
     * Crear una nueva instancia de GraphicEntity a partir del nombre del xml de los
     * recursos.
     * @param name El nombre del xml de spritesheet de los recursos.
     * @return Una nueva instancia de GraphicEntity.
     */
    public GraphicEntity createGraphicEntity(String name) {
        Boolean exists = false;
        GraphicEntity graphicEntity = null;

        for(GraphicEntity entity : this.entities){
            if(entity.getName().equals(name) ) {
                exists = true;
                graphicEntity = entity;
            }
        }

        if(!exists){
            Sprite sprite = this.atlas.createSprite(name);
            graphicEntity = new Background(sprite);
            entities.add(graphicEntity);
        }
        return graphicEntity;
    }

    /*
     * Obtiene el SpriteBatch que se usará para dibujar los sprites.
     * @return SpriteBatch que se usará para dibujar los sprites.
     */
    public SpriteBatch getSpriteBatch() {
        return this.spriteBatch;
    }
}
