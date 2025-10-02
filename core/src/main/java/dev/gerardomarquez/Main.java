package dev.gerardomarquez;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import dev.gerardomarquez.entities.Background;
import dev.gerardomarquez.entities.GraphicEntity;
import dev.gerardomarquez.entities.GraphicEntityFactory;
import dev.gerardomarquez.entities.Player;
import dev.gerardomarquez.utils.Constants;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    
    private SpriteBatch batch;
    private GraphicEntityFactory graphicEntityFactory;
    private Background background;
    private Player player;

    float stateTime = 0f;
    float frameDuration = 0.1f;

    @Override
    public void create() {
        this.graphicEntityFactory = GraphicEntityFactory.getInstance();
        this.background = (Background) graphicEntityFactory.createGraphicEntity(Constants.SPRITE_NAME_BACKGROUND);
        this.player = (Player) graphicEntityFactory.createGraphicEntity(Constants.SPRITE_NAME_PLANE);
        batch = graphicEntityFactory.getSpriteBatch();
    }

    @Override
    public void render() {

        ScreenUtils.clear(0, 0, 0, 1f);
        batch.begin();
        background.draw(batch);
        player.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
