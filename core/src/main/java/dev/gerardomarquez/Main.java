package dev.gerardomarquez;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import dev.gerardomarquez.entities.Background;
import dev.gerardomarquez.entities.GraphicEntityFactory;
import dev.gerardomarquez.entities.GroundAndRockMapp;
import dev.gerardomarquez.entities.Player;
import dev.gerardomarquez.utils.Constants;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    
    private SpriteBatch batch;
    private GraphicEntityFactory graphicEntityFactory;
    private Background background;
    private Player player;
    private GroundAndRockMapp groundMapp;
    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, camera);
        camera.position.set(Constants.VIRTUAL_WIDTH / 2f, Constants.VIRTUAL_HEIGHT / 2f, 0);
        camera.update();

        this.graphicEntityFactory = GraphicEntityFactory.getInstance();
        this.background = (Background) graphicEntityFactory.createGraphicEntity(Constants.SPRITE_NAME_BACKGROUND);
        this.player = (Player) graphicEntityFactory.createGraphicEntity(Constants.SPRITE_NAME_PLANE);
        this.groundMapp = (GroundAndRockMapp) graphicEntityFactory.createGraphicEntity(Constants.SPRITE_NAME_GROUND);
        this.batch = graphicEntityFactory.getSpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        Float delta = Gdx.graphics.getDeltaTime();
        ScreenUtils.clear(0, 0, 0, 1f);
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        batch.begin();
        
        background.draw(batch);
        groundMapp.draw(batch);
        player.draw(batch);
        
        batch.end();

        player.gameplay(delta );
        player.applyGravity(delta );
        groundMapp.move(delta);
        groundMapp.gameplay();
        groundMapp.triggerRock(delta);

        if(groundMapp.collision(player) ){
            player.stopPlayer();
        }


        /* Pintar colisiones solo prueba */
        /*this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);        

        shapeRenderer.setColor(Color.RED);

        for(Polygon polygon: player.getPolygons() ){
            this.shapeRenderer.polygon(polygon.getTransformedVertices() );
        }

        for(Polygon polygon: this.groundMapp.getPolygons() ){
            this.shapeRenderer.polygon(polygon.getTransformedVertices() );
        }

        this.shapeRenderer.end();*/

        /* Fin de pintar colisiones solo prueba */
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
