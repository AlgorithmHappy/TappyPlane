package dev.gerardomarquez.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Shape2D;
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
            switch (name) {
                case Constants.SPRITE_NAME_BACKGROUND:
                    Sprite spriteBackgroud = this.atlas.createSprite(name);
                    graphicEntity = new Background(spriteBackgroud);
                    entities.add(graphicEntity);
                    break;

                case Constants.SPRITE_NAME_PLANE:
                    Plane Plane1 = new Plane(
                        this.atlas, 
                        Constants.PLANE_COLOR.BLUE, 
                        1
                    ); // El enum cambiara dependiendo de lo que elija el jugador en un posible menu
                    Plane1.setCollisionShape(this.getShapeBySpriteName(name + Constants.PLANE_COLOR.BLUE.getValue() ) );

                    Plane Plane2 = new Plane(
                        this.atlas, 
                        Constants.PLANE_COLOR.BLUE, 
                        2
                    );
                    Plane2.setCollisionShape(this.getShapeBySpriteName(name + Constants.PLANE_COLOR.BLUE.getValue() ) );

                    Plane Plane3 = new Plane(
                        this.atlas, 
                        Constants.PLANE_COLOR.BLUE, 
                        3
                    );
                    Plane3.setCollisionShape(this.getShapeBySpriteName(name + Constants.PLANE_COLOR.BLUE.getValue() ) );

                    Plane[] arrayGraphicEntityPlanes = new Plane[]{
                        Plane1,
                        Plane2,
                        Plane3
                    };

                    graphicEntity = new Player(arrayGraphicEntityPlanes);
                    entities.add(graphicEntity);
                    break;

                case Constants.SPRITE_NAME_GROUND:
                    graphicEntity = new GroundMapp(atlas, Constants.GROUND_KIND.DIRT, Constants.GROUND_KIND.DIRT);
                    entities.add(graphicEntity);
                    break;
            
                default:
                    Sprite spriteDefault = this.atlas.createSprite(name);
                    graphicEntity = new Background(spriteDefault);
                    entities.add(graphicEntity);
                    break;
            }
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

    public List<Shape2D> getShapeBySpriteName(String name){
        List<Shape2D> polygonsOrCircles = new ArrayList<>();
        try {
            XmlReader reader = new XmlReader();
            XmlReader.Element root = reader.parse(Gdx.files.internal(Constants.PATH_COLLISIONS) );

            for (XmlReader.Element bodyElem : root.getChildrenByName(Constants.COLLISION_TAG_BODY) ) {
                String bodyName = bodyElem.getAttribute(Constants.COLLISION_ATRIBUTE_NAME);
                if(bodyName.equals(name) ){
                    for (XmlReader.Element polyElem : bodyElem.getChildrenByName(Constants.COLLISION_TAG_POLYGON) ) {
                        String verticesStr = polyElem.getText().trim();
                        String[] coords = verticesStr.split(Constants.COLLISION_POINTS_SEPARATION);
                        float[] vertices = new float[coords.length];
                        for (int i = 0; i < coords.length; i++) {
                            vertices[i] = Float.parseFloat(coords[i].trim() );
                        }
                        polygonsOrCircles.add(new Polygon(vertices) );
                    }

                    for (XmlReader.Element circleElem : bodyElem.getChildrenByName(Constants.COLLISION_TAG_CIRCLE) ) {
                        Float x = circleElem.getFloat(Constants.COLLISION_CIRCLE_X);
                        Float y = circleElem.getFloat(Constants.COLLISION_CIRCLE_Y);
                        Float r = circleElem.getFloat(Constants.COLLISION_CIRCLE_RADIO);
                        polygonsOrCircles.add(new Circle(x, y, r));
                    }
                }
                


                // cuerpos.put(bodyName, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return polygonsOrCircles;
    }
}
