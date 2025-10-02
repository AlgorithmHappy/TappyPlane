package dev.gerardomarquez.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.gerardomarquez.utils.Constants;

/*
 * {@inheritDoc}
 */
public class Background implements GraphicEntity {

    /*
     * Nombre del fondo, usado para identificar el sprite en el atlas.
     */
    private String name;

    /*
     * Sprite que representa el fondo.
     */
    private Sprite sprite;

    /*
     * Constructor que inicializa el fondo con un nombre.
     * @param sprite El sprite que representa el fondo.
     */
    public Background(Sprite sprite) {
        this.name = Constants.SPRITE_NAME_BACKGROUND;
        this.sprite = sprite;
        this.sprite.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
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
     * {@inheritDoc}
     */
    @Override
    public Sprite getSprite() {
        return this.sprite;
    }
}
