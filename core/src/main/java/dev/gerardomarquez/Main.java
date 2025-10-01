package dev.gerardomarquez;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import dev.gerardomarquez.entities.GraphicEntity;
import dev.gerardomarquez.entities.GraphicEntityFactory;
import dev.gerardomarquez.utils.Constants;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    
    private SpriteBatch batch;
    private GraphicEntityFactory graphicEntityFactory;
    private GraphicEntity background;
    private GraphicEntity planeBlue1;
    private GraphicEntity planeBlue2;
    private GraphicEntity planeBlue3;
    private Array<Sprite> frames;

    @Override
    public void create() {
        graphicEntityFactory = GraphicEntityFactory.getInstance();
        background = graphicEntityFactory.createGraphicEntity(Constants.SPRITE_NAME_BACKGROUND);
        planeBlue1 = graphicEntityFactory.createGraphicEntity(Constants.SPRITE_NAME_PLANE_BLUE_1);
        planeBlue2 = graphicEntityFactory.createGraphicEntity(Constants.SPRITE_NAME_PLANE_BLUE_2);
        planeBlue3 = graphicEntityFactory.createGraphicEntity(Constants.SPRITE_NAME_PLANE_BLUE_3);
        batch = graphicEntityFactory.getSpriteBatch();
        frames = new Array<>();
        frames.add(planeBlue1.getSprite() )
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1f);
        batch.begin();
        background.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
